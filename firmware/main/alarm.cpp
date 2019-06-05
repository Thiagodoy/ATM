#include <Arduino.h>
#include "alarm.h"

#ifndef LOGGER_H
  #include "logger.h"
#endif

Alarm::Alarm(byte pRelayMode, int pRedDO, int pYellowDO){
  this->relayMode;
  this->redDO = pRedDO;
  this->yellowDO = pYellowDO;
}

void Alarm::setup(){
  pinMode(this->redDO,OUTPUT);
  pinMode(this->yellowDO,OUTPUT);
  Logger::debug("Alarm setup");
}

void Alarm::turnOnRed(){
  digitalWrite(this->redDO, HIGH ^ this->relayMode);
  Logger::debug("Red light is turned on");  
}

void Alarm::turnOffRed(){
  digitalWrite(this->redDO, LOW ^ this->relayMode);
  Logger::debug("Red light is turned off");
}

void Alarm::turnOnYellow(){
  digitalWrite(this->yellowDO, HIGH ^ this->relayMode);
  Logger::debug("Yellow light is turned on");
}
void Alarm::turnOffYellow(){
  digitalWrite(this->yellowDO, LOW ^ this->relayMode);
  Logger::debug("Yellow light is turned off");
}
int Alarm::getStatusRed(){
  return digitalRead(this->redDO);
}
int Alarm::getStatusYellow(){
  return digitalRead(this->redDO);
}

