#include <Arduino.h>
#include <EEPROM.h>
#include "box.h"


#ifndef LOGGER_H
  #include "logger.h"
#endif

Box::Box(byte pRelayMode, int pOpenerDO, int pCloserDO, int pClosedDI, int pOpenedDI, int pBlockedDI){
  this->relayMode = pRelayMode;
  //Effectors
  this->openerDO = pOpenerDO;
  this->closerDO = pCloserDO;
  //Sensors
  this->closedDI = pClosedDI;
  this->openedDI = pOpenedDI;
  this->blockedDI = pBlockedDI;
};

void Box::setup(void){
  pinMode(this->openerDO, OUTPUT);
  pinMode(this->closerDO, OUTPUT);
  pinMode(this->closedDI, INPUT);
  pinMode(this->openedDI, INPUT);
  pinMode(this->blockedDI, INPUT);
  
  //set pulldown resistors
  digitalWrite(this->closedDI, LOW);
  digitalWrite(this->openedDI, LOW);
  digitalWrite(this->blockedDI, LOW);
};

int Box::getState(void){
  Logger::debug(String("Full open sensor: ") + digitalRead(this->openedDI));
  Logger::debug(String("Close sensor: ") + digitalRead(this->closedDI));
  Logger::debug(String("Blocked sensor: ") + digitalRead(this->blockedDI));

  if(isFullOpened() && isClosed())
    return this->CODE_FAIL;

  if(isClosed())
    return this->CODE_CLOSED;

  if(isFullOpened())
    if(!isBlocked())
      return this->CODE_OPEN_FULL;
    else
      return this->CODE_BLOCKED;
  else
    if(!isBlocked())
      return this->CODE_OPEN_HALF;
    else
      return this->CODE_BLOCKED;
};

int Box::open(void){

  if(!isFullOpened()){
    this->turnOnOpenerMotor();
    long startTime = millis();
    long stopTime = startTime + Box::TIMEOUT;
    
    while(millis() < stopTime && !isFullOpened()){
      if(isBlocked()){
        stopTime += Box::TIMEOUT;
        this->openingCallback(this->CODE_BLOCKED);
      }else
        this->openingCallback(this->CODE_WORKING_OPENING);
      delay(1000);
    }
        
    this->turnOffOpenerMotor();
  }
  
  return this->getState();
};

int Box::close(void){
  if(!isClosed() && !isBlocked()){
    this->turnOnCloserMotor();
    long startTime = millis();
    long stopTime = startTime + Box::TIMEOUT;
    
    while(millis() < stopTime && !isClosed()){
      if(isBlocked()){
        stopTime += Box::TIMEOUT;
        this->closingCallback(this->CODE_BLOCKED);
      }else
        this->closingCallback(this->CODE_WORKING_CLOSING);      
      delay(1000);
    }
    this->turnOffCloserMotor();
  }

  return this->getState();
};

bool Box::isFullOpened(void){
  return digitalRead(this->openedDI);
};

bool Box::isClosed(void){
  return digitalRead(this->closedDI);
};

bool Box::isBlocked(void){
  return digitalRead(this->blockedDI);
};

void Box::turnOnOpenerMotor(void){
  digitalWrite(this->openerDO, HIGH ^ this->relayMode);
};

void Box::turnOffOpenerMotor(void){
  digitalWrite(this->openerDO, LOW ^ this->relayMode);
};

void Box::turnOnCloserMotor(void){
  digitalWrite(this->closerDO, HIGH ^ this->relayMode);
};

void Box::turnOffCloserMotor(void){
  digitalWrite(this->closerDO, LOW ^ this->relayMode);
};

void Box::setOpeningCallback(void (*callback)){
  this->openingCallback = callback;
};

void Box::setClosingCallback(void (*callback)){
  this->closingCallback = callback;
};

