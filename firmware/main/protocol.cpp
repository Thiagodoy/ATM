#include <Arduino.h>
#include "protocol.h"

#ifndef LOGGER_H
  #include "logger.h"
#endif

/******** Commands ***********/
static const char* Protocol::COMMAND_STATE_D1        = "STATE_D1";
static const char* Protocol::COMMAND_STATE_D2        = "STATE_D2";
static const char* Protocol::COMMAND_STATE_D3        = "STATE_D3";
static const char* Protocol::COMMAND_STATE_D4        = "STATE_D4";

static const char* Protocol::COMMAND_INVENTORY_D1    = "INVENTORY_D1";
static const char* Protocol::COMMAND_INVENTORY_D2    = "INVENTORY_D2";
static const char* Protocol::COMMAND_INVENTORY_D3    = "INVENTORY_D3";
static const char* Protocol::COMMAND_INVENTORY_D4    = "INVENTORY_D4";

static const char* Protocol::COMMAND_DISPENSE_D1     = "DISPENSE_D1";
static const char* Protocol::COMMAND_DISPENSE_D2     = "DISPENSE_D2";
static const char* Protocol::COMMAND_DISPENSE_D3     = "DISPENSE_D3";
static const char* Protocol::COMMAND_DISPENSE_D4     = "DISPENSE_D4";

static const char* Protocol::COMMAND_RECHARGE_D1     = "RECHARGE_D1:";
static const char* Protocol::COMMAND_RECHARGE_D2     = "RECHARGE_D2:";
static const char* Protocol::COMMAND_RECHARGE_D3     = "RECHARGE_D3:";
static const char* Protocol::COMMAND_RECHARGE_D4     = "RECHARGE_D4:";
                   
static const char* Protocol::COMMAND_BOX_STATE       = "BOX_STATE";
static const char* Protocol::COMMAND_BOX_OPEN        = "BOX_OPEN";
static const char* Protocol::COMMAND_BOX_CLOSE       = "BOX_CLOSE";
static const char* Protocol::COMMAND_WEIGHT          = "WEIGHT";
                   
static const char* Protocol::COMMAND_ALARM_RED       = "ALARM_RED";
static const char* Protocol::COMMAND_ALARM_YELLOW    = "ALARM_YELLOW";
static const char* Protocol::COMMAND_ALARM_OFF       = "ALARM_OFF";
                   
static const char* Protocol::COMMAND_FULLSTATE       = "FULLSTATE";

static const char* Protocol::COMMAND_KEEP_ALIVE       = "KEEP_ALIVE";

/******** ResponsesProtocol:: **********/
static const char* Protocol::RESP_SUCCESS               = "[RESP]SUCCESS";                                
static const char* Protocol::RESP_FAIL                  = "[RESP]FAIL";

static const char* Protocol::RESP_IDLE_D                = "[RESP]IDLE_D";
static const char* Protocol::RESP_INVENTORY_D           = "[RESP]INVENTORY_D";
static const char* Protocol::RESP_WORKING_D             = "[RESP]WORKING_D";
static const char* Protocol::RESP_READY_TO_REMOVE_D     = "[RESP]READY_TO_REMOVE_D";
static const char* Protocol::RESP_WARNING_EMPTY_D       = "[RESP]WARNING_EMPTY_D";
static const char* Protocol::RESP_WARNING_LOW_LEVEL_D   = "[RESP]WARNING_LOW_LEVEL_D";
static const char* Protocol::RESP_ERROR_NOT_DISPENSED_D = "[RESP]ERROR_NOT_DISPENSED_D";
static const char* Protocol::RESP_ERROR_BLOCKED_D       = "[RESP]ERROR_BLOCKED_D";  
                   
static const char* Protocol::RESP_BOX_CLOSED            = "[RESP]BOX_CLOSED";
static const char* Protocol::RESP_BOX_OPEN_FULL         = "[RESP]BOX_OPEN_FULL";
static const char* Protocol::RESP_BOX_OPEN_HALF         = "[RESP]BOX_OPEN_HALF";
static const char* Protocol::RESP_BOX_BLOCKED           = "[RESP]BOX_BLOCKED";
static const char* Protocol::RESP_BOX_WORKING_OPENING   = "[RESP]BOX_WORKING_OPENING";
static const char* Protocol::RESP_BOX_WORKING_CLOSING   = "[RESP]BOX_WORKING_CLOSING";

static const char* Protocol::RESP_UNRECOGNIZED          = "[RESP]UNRECOGNIZED";
                       
/******** CallbacksProtocol:: **********/
static const char* Protocol::CALLBACK_FAIL                  = "[CB]FAIL";
static const char* Protocol::CALLBACK_SCALE_OFFLINE         = "[CB]SCALE_OFFLINE";
static const char* Protocol::CALLBACK_IDLE_D                = "[CB]IDLE_D";
static const char* Protocol::CALLBACK_WORKING_D             = "[CB]WORKING_D";
static const char* Protocol::CALLBACK_READY_TO_REMOVE_D     = "[CB]READY_TO_REMOVE_D";
static const char* Protocol::CALLBACK_WARNING_EMPTY_D       = "[CB]CALLBACK_WARNING_EMPTY_D";
static const char* Protocol::CALLBACK_WARNING_LOW_LEVEL_D   = "[CB]CALLBACK_WARNING_LOW_LEVEL_D";
static const char* Protocol::CALLBACK_ERROR_BLOCKED_D       = "[CB]CALLBACK_ERROR_BLOCKED_D";
static const char* Protocol::CALLBACK_BOX_WORKING_OPENING   = "[CB]BOX_WORKING_OPENING";
static const char* Protocol::CALLBACK_BOX_WORKING_CLOSING   = "[CB]BOX_WORKING_CLOSING";

static const char* Protocol::CALLBACK_UNRECOGNIZED_COMM    = "[CB]UNRECOGNIZED_COMM";


void Protocol::communicate(void){
  if(Serial.available()){
    String msg = Serial.readString();

    Logger::debug("<ECO> " + msg);

    if(msg.length() > Protocol::MAX_COMMAND_LENGHT)
      this->send(String(Protocol::CALLBACK_UNRECOGNIZED_COMM));
  
    this->receive(msg);
  }
}

static String Protocol::translateDispenserState(int dispenserState, int dispenserId){
  Logger::debug("Translate dispenser code " + String(dispenserState));
   switch(dispenserState){
    case Dispenser::CODE_IDLE: return String(Protocol::RESP_SUCCESS);
    case Dispenser::CODE_WORKING: return String(Protocol::RESP_WORKING_D) + dispenserId;
    case Dispenser::CODE_READY_TO_REMOVE: return String(Protocol::RESP_READY_TO_REMOVE_D) + dispenserId;
    case Dispenser::CODE_WARN_EMPTY: return String(Protocol::RESP_WARNING_EMPTY_D) + dispenserId;
    case Dispenser::CODE_WARN_LOW_LEVEL: return String(Protocol::RESP_WARNING_LOW_LEVEL_D) + dispenserId;
    case Dispenser::CODE_ERROR_NOT_DISPENSED: return String(Protocol::RESP_ERROR_NOT_DISPENSED_D) + dispenserId;
    case Dispenser::CODE_ERROR_BLOCKED: return String(Protocol::RESP_ERROR_BLOCKED_D) + dispenserId;
    default: return Protocol::RESP_FAIL;
  };
}

static String Protocol::translateBoxState(int boxState){
  Logger::debug("Translate box code " + String(boxState));
  switch(boxState){
    case Box::CODE_CLOSED: return String(Protocol::RESP_BOX_CLOSED);
    case Box::CODE_OPEN_FULL: return String(Protocol::RESP_BOX_OPEN_FULL);
    case Box::CODE_OPEN_HALF: return String(Protocol::RESP_BOX_OPEN_HALF);
    case Box::CODE_BLOCKED: return String(Protocol::RESP_BOX_BLOCKED);
    case Box::CODE_WORKING_OPENING: return String(Protocol::CALLBACK_BOX_WORKING_OPENING);
    case Box::CODE_WORKING_CLOSING: return String(Protocol::CALLBACK_BOX_WORKING_CLOSING);
    default: return Protocol::RESP_FAIL;
  };
}
    
void Protocol::setAlarm(Alarm *pAlarm){
  this->alarm = pAlarm;
}

void Protocol::setScale(Scale *pScale){
  this->scale = pScale;
}

void Protocol::setDispenser1(Dispenser *pDispenser1){
  this->dispenser1 = pDispenser1;
  this->dispenser1->setWorkingCallback(&Protocol::dispenser1WorkingCallback);
}

void Protocol::setBox(Box *pBox){
  this->box = pBox;
  this->box->setOpeningCallback(&Protocol::boxOpeningCallback);
  this->box->setClosingCallback(&Protocol::boxClosingCallback);
}

void Protocol::send(String message){
  Serial.println(message);
}

void Protocol::sendDispenserCallback(int dispenserState, int dispenserId){
  Logger::debug("Dispenser callback code " + String(dispenserState));
  String message;
  switch(dispenserState){
    case Dispenser::CODE_IDLE : message = String(Protocol::CALLBACK_IDLE_D) + dispenserId; break;
    case Dispenser::CODE_WORKING: message = String(Protocol::CALLBACK_WORKING_D) + dispenserId; break;
    case Dispenser::CODE_READY_TO_REMOVE: message = String(Protocol::CALLBACK_READY_TO_REMOVE_D) + dispenserId; break;
    case Dispenser::CODE_WARN_EMPTY: message = String(Protocol::CALLBACK_WARNING_EMPTY_D) + dispenserId; break;
    case Dispenser::CODE_WARN_LOW_LEVEL: message = String(Protocol::CALLBACK_WARNING_LOW_LEVEL_D) + dispenserId; break;
    case Dispenser::CODE_ERROR_BLOCKED: message = String(Protocol::CALLBACK_ERROR_BLOCKED_D) + dispenserId; break;
    default: message = Protocol::CALLBACK_FAIL;
  };

  this->send(message);
}

void Protocol::sendScaleCallback(int scaleState){
  Logger::debug("Scale callback " + String(scaleState));
  String message;
  switch(scaleState){
    case Scale::CODE_SCALE_OFFLINE: message = String(Protocol::CALLBACK_SCALE_OFFLINE); break;
    default: message = Protocol::CALLBACK_FAIL;
  };

  this->send(message);
}

void Protocol::receive(String message){
  /************************
   ********* Alarm ********
   ************************/
  if(message == String(Protocol::COMMAND_ALARM_OFF)){
      this->alarm->turnOffRed();
      this->alarm->turnOffYellow();
      
      this->send(String(Protocol::RESP_SUCCESS));
      return;
  }
  
  if(message == String(Protocol::COMMAND_ALARM_RED)){
    this->alarm->turnOnRed();
    
    this->send(String(Protocol::RESP_SUCCESS));
    return;
  }

  if(message == String(Protocol::COMMAND_ALARM_YELLOW)){
    this->alarm->turnOnYellow();
    
    this->send(String(Protocol::RESP_SUCCESS));
    return;
  }

  /************************
   ****** Dispenser1 ******
   ************************/
  if(message == String(Protocol::COMMAND_STATE_D1)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_STATE_D1));
    int iStateD1 = this->dispenser1->getState();
    String resp = Protocol::translateDispenserState(iStateD1,this->dispenser1->getId());
    
    this->send(resp);
    return;
  }

  if(message == String(Protocol::COMMAND_INVENTORY_D1)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_INVENTORY_D1));
    int counter = this->dispenser1->getCounter();
    String resp = String(Protocol::RESP_INVENTORY_D) + this->dispenser1->getId() + ":" + counter;
    
    this->send(resp);
    return;
  }
  
  if(message == String(Protocol::COMMAND_DISPENSE_D1)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_DISPENSE_D1));
    int iStatus = this->dispenser1->dispense();
    String sStatus = Protocol::translateDispenserState(iStatus,this->dispenser1->getId());
    
    this->send(sStatus);
    return;
  }
  
  if(message.startsWith(Protocol::COMMAND_RECHARGE_D1)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_RECHARGE_D1));
    int li = message.lastIndexOf(':') + 1;
    String number = message.substring(li, li+3);
    Logger::debug("Number of envelops parsed: " + number);
    this->dispenser1->setCounter(number.toInt());

    this->send(String(Protocol::RESP_SUCCESS));
    return;
  }

  /************************
   ********* Box **********
   ************************/
  if(message == String(Protocol::COMMAND_BOX_STATE)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_BOX_STATE));
    int iState = this->box->getState();
    String sState = this->translateBoxState(iState);
    
    this->send(sState);
    return;
  }

  if(message == String(Protocol::COMMAND_BOX_OPEN)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_BOX_OPEN));
    int iState = this->box->open();
    String sState = this->translateBoxState(iState);
    
    this->send(sState);
    return;
  }
  
  if(message == String(Protocol::COMMAND_BOX_CLOSE)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_BOX_CLOSE));
    int iState = this->box->close();
    String sState = this->translateBoxState(iState);
    
    this->send(sState);
    return;
  }

  /************************
   ****** Keep alive ******
   ************************/
   if(message == String(Protocol::COMMAND_KEEP_ALIVE)){
    Logger::debug("Command recognized " + String(Protocol::COMMAND_KEEP_ALIVE));
    
    this->send(Protocol::RESP_SUCCESS);
    return;
   }

   this->send(Protocol::RESP_UNRECOGNIZED);
}

static void Protocol::dispenser1WorkingCallback(Dispenser *dispenser){
  Serial.println(String(Protocol::CALLBACK_WORKING_D) + dispenser->getId()); 
}

static void Protocol::boxOpeningCallback(int boxState){
  Serial.println(String(Protocol::translateBoxState(boxState))); 
}

static void Protocol::boxClosingCallback(int boxState){
  Serial.println(String(Protocol::translateBoxState(boxState))); 
}
