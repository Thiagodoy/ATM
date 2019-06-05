#include <Arduino.h>
#include "logger.h"

static int Logger::level = -1;

static const int Logger::ERROR_LEVEL = 0;
static const int Logger::WARN_LEVEL = 1;
static const int Logger::INFO_LEVEL = 2;
static const int Logger::DEBUG_LEVEL = 3;

static void Logger::error(String message){
   if(Logger::level >= Logger::ERROR_LEVEL)
     Serial.println("[ERROR] " + message);
}

static void Logger::warn(String message){
   if(Logger::level >= Logger::WARN_LEVEL)
     Serial.println("[WARN] " + message);  
}

static void Logger::info(String message){
   if(Logger::level >= Logger::INFO_LEVEL)
     Serial.println("[INFO] " + message);  
}

static void Logger::debug(String message){
   if(Logger::level >= Logger::DEBUG_LEVEL)
     Serial.println("[DEBUG] " + message);
}

static void Logger::setLevel(int pLevel){
  Logger::level = pLevel;
}

static void Logger::setInactive(void){
  Logger::level = -1;
}

