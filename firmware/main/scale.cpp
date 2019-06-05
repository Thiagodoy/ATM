#include <Arduino.h>
#include "scale.h"

#ifndef LOGGER_H
  #include "logger.h"
#endif

void Scale::setup(void){
  Serial1.begin(9600);
  Serial1.setTimeout(3000);

  Logger::debug("Serial1 baud rate: " + String(9600));
  Logger::debug("Serial1 timeout: " + String(3000));
}

float Scale::getWeight(void){
  Serial1.write("L\r");
  if(Serial1.available()){
    Logger::debug("Serial1 data available");
    Serial1.write("I\r");
    String ret = Serial1.readString();
    ret.trim();
    ret = ret.substring(0,ret.length()-2);
    Logger::debug("Scales returns weight: " + String(ret));
    return ret.toFloat();
  }
}

bool Scale::isOnline(void){
  Serial1.write("I\r");
  String msg = Serial1.readString();
  Logger::debug("Scales returns online: " + msg);
  if(msg != "")
    return true;
  else
    return false;
}

