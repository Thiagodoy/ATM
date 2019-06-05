#include <Arduino.h>
#include <EEPROM.h>
#include "dispenser.h"


#ifndef LOGGER_H
  #include "logger.h"
#endif


Dispenser::Dispenser(int pId, byte pRelayMode, int pInjectorMotorDO, int pRemotionObstacleDI, int pEmptyObstacleDI){
  this-> id = pId;
  this->relayMode = pRelayMode;
  this->injectorMotorDO = pInjectorMotorDO;
  this->remotionObstacleDI = pRemotionObstacleDI;
  this->emptyObstacleDI = pEmptyObstacleDI;
};

void Dispenser::setup(void){
  pinMode(this->injectorMotorDO,OUTPUT);
  pinMode(this->remotionObstacleDI,INPUT);
  pinMode(this->emptyObstacleDI,INPUT);
  
  //set pulldown resistors
  digitalWrite(this->remotionObstacleDI,LOW);
  digitalWrite(this->emptyObstacleDI,LOW);
  
  this->counter = EEPROM.read(this->id*2);
  Logger::debug("Eeprom value of counter:" + String(this->counter));
};

void Dispenser::configure(int pCapacity, int pThroughput, int pMotorTimeout, int pRemoveTimeout){
  this->capacity = pCapacity;
  this->throughput = pThroughput;
  this->motorTimeout = pMotorTimeout;
  this->removeTimeout = pRemoveTimeout;
}

int Dispenser::dispense(){
  if(counter < 0){ //check internal state error
    Logger::debug("Dispenser " + String(this->id) + " counter has negative value");
    return Dispenser::CODE_FAIL;
  }

  if(isEmpty()){ //check if dispenser is empty
    this->state = Dispenser::CODE_WARN_EMPTY;
    Logger::debug("Dispenser " + String(this->id) + " is empty");
    return this->state;
  }

  if(isBlocked()){ //check if the dispenser is blocked
    this->state = Dispenser::CODE_ERROR_BLOCKED;
    Logger::debug("Dispenser " + String(this->id) + " is blocked");
    return this->state;
  }
    
  if (this->state != Dispenser::CODE_IDLE && this->state != this->CODE_WARN_LOW_LEVEL){ //check state
    Logger::debug("Dispenser " + String(this->id) + " can't dispense " + String(this->state));
    return this->state;
  }

  Logger::debug("Dispenser " + String(this->id) + ", starting dispenser injection");
  //Dispensation


  bool timeover = false;
  bool attemptover = false;

  /*
   * Loop of attemptions
   */
  int numberOfAttempts = 0;
  while(!this->isBlocked() && !attemptover){
    //sends the state CODE_WORKING if there was a request about dispenser state
    this->workingCallback(this);
    unsigned long timeout = millis() + this->motorTimeout;
    Logger::debug("Dispenser " + String(this->id) + ", motor turned on in port " + this->injectorMotorDO +  " at " + String(millis()) + " till " + String(timeout));
    this->turnOnMotor();
    
    Logger::debug("Dispenser " + String(this->id) + " attempt " + String(numberOfAttempts));
    //
    //Loop of timem until push
    //
    while(!this->isBlocked() && !timeover){ 
      if(millis() > timeout){
        timeover = true;
        Logger::debug("Dispenser " + String(this->id) + "timeover" );
      }
      delay(10);
    }
    this->turnOnMotor();
    Logger::debug("Dispenser " + String(this->id) + ", motor turned ON");

    delay(4000); //extra time insection
    this->turnOffMotor();
    Logger::debug("Dispenser " + String(this->id) + ", motor turned OFF");
    
    
    if(numberOfAttempts < MAX_DISPENSATION_ATTEMPS){
      timeover = false;
      ++numberOfAttempts;
    }else{
      attemptover = true;
      timeover = true;
    }
  }

  if(this->isBlocked()){
    this->state = Dispenser::CODE_READY_TO_REMOVE;
    --this->counter;
    Logger::debug("Dispenser " + String(this->id) + " pushed!");
  }else if(timeover){
    this->state = Dispenser::CODE_ERROR_NOT_DISPENSED;
    Logger::debug("Dispenser " + String(this->id) + " time over after several attempts.");
  }else{
    this->state = Dispenser::CODE_FAIL;
    Logger::debug("Dispenser " + String(this->id) + " illegal internal state.");
  }
  return this->state;
};

int Dispenser::getState(){

  Logger::debug("Empty sensor: " + String(digitalRead(this->remotionObstacleDI)));
  Logger::debug("Block sensor: " + String(digitalRead(this->emptyObstacleDI)));

  this->state = Dispenser::CODE_IDLE;
  
  if(this->counter < 0){ //check internal state error
    Logger::debug("Dispenser " + String(this->id) + " counter has negative value");
    this->state = Dispenser::CODE_FAIL;
  }
  
  if(this->counter < this->throughput){
    this->state = Dispenser::CODE_WARN_LOW_LEVEL;
    Logger::debug("Dispenser " + String(this->id) + " counter has low level: " + this->counter);
  }
  
  if(this->isEmpty() || this->counter == 0){ //check if dispenser is empty
    this->state = Dispenser::CODE_WARN_EMPTY;
    Logger::debug("Dispenser " + String(this->id) + " is empty");
  }
  
  long ellapsedTime = millis() - lastDispensation;
  if(this->isBlocked()) //check if the dispenser is blocked
    if(ellapsedTime < removeTimeout){
      this->state = Dispenser::CODE_READY_TO_REMOVE;
      Logger::debug("Dispenser " + String(this->id) + ", is ready to remove");
    }else{
      this->state = Dispenser::CODE_ERROR_BLOCKED;
      Logger::debug("Dispenser " + String(this->id) + ", time is ellapsed and the envelop was not removed");
    }

  return this->state;
};

int Dispenser::getId(void){
  return this->id;
};

void Dispenser::setCounter(int pCounter){
  this->counter = pCounter;
  Logger::debug("Dispenser " + String(this->id) + ", counter set to " + String(pCounter));
  EEPROM.write(this->id*2, this->counter);
};

int Dispenser::getCounter(void){
  return this->counter;
};

void Dispenser::setWorkingCallback(void (*pCallback)){
  this->workingCallback = pCallback;
};

bool Dispenser::isEmpty(){
  return digitalRead(this->emptyObstacleDI) == HIGH;
};

bool Dispenser::isBlocked(){
  return digitalRead(this->remotionObstacleDI) == LOW;
};

void Dispenser::turnOnMotor(){
  digitalWrite(this->injectorMotorDO, HIGH ^ this->relayMode); //turns on the motor
};

void Dispenser::turnOffMotor(){
  digitalWrite(this->injectorMotorDO, LOW ^ this->relayMode); //turns on the motor
};
