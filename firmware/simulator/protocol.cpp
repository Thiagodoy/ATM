#include <Arduino.h>
#include "protocol.h"

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

static const char* Protocol::CALLBACK_UNRECOGNIZED_COMM     = "[CB]UNRECOGNIZED_COMM";

/******** Other simulations **********/
static const char* Protocol::COMMAND_SCALE_TURNON           = "L";
static const char* Protocol::COMMAND_SCALE_PRINTP           = "P";
static const char* Protocol::COMMAND_SCALE_PRINTI           = "I";

static const char* Protocol::COMMAND_CUBOMETER_MEASURES      = "MEASURES";

void Protocol::communicate(void){
  if(Serial.available()){
    String msg = Serial.readString();
    
    if(msg.length() > Protocol::MAX_COMMAND_LENGHT)
      this->send(String(Protocol::CALLBACK_UNRECOGNIZED_COMM));
  
    this->receive(msg);
  }
}

void Protocol::send(String message){
  Serial.println(message);
}

void Protocol::receive(String message){
  /************************
   ********* Alarm ********
   ************************/
  if(message == String(Protocol::COMMAND_ALARM_OFF)){
      this->send(String(Protocol::RESP_SUCCESS));
      return;
  }
  
  if(message == String(Protocol::COMMAND_ALARM_RED)){
    this->send(String(Protocol::RESP_SUCCESS));
    return;
  }

  if(message == String(Protocol::COMMAND_ALARM_YELLOW)){
    this->send(String(Protocol::RESP_SUCCESS));
    return;
  }

  /************************
   ****** Dispenser1 ******
   ************************/
  if(message == String(Protocol::COMMAND_STATE_D1)){
    this->send(String(Protocol::RESP_IDLE_D) + "1");
    return;
  }

  if(message == String(Protocol::COMMAND_INVENTORY_D1)){
    this->send(String(Protocol::RESP_INVENTORY_D) + "1:10");
    return;
  }
  
  if(message == String(Protocol::COMMAND_DISPENSE_D1)){
    this->send(String(Protocol::RESP_WORKING_D) + "1");
    delay(1000);
     this->send(String(Protocol::CALLBACK_WORKING_D) + "1");
    delay(1000);
    this->send(String(Protocol::RESP_READY_TO_REMOVE_D) + "1");
    delay(2000);
    this->send(String(Protocol::RESP_IDLE_D) + "1");
    delay(2000);
    return;
  }
  
  if(message.startsWith(Protocol::COMMAND_RECHARGE_D1)){
    this->send(String(Protocol::RESP_SUCCESS));
    return;
  }

  /************************
   ********* Box **********
   ************************/
  if(message == String(Protocol::COMMAND_BOX_STATE)){
    if(this->boxFullopened && this->boxClosed){
      this->send(String(Protocol::RESP_FAIL));
      return;
    }

  if(this->boxClosed){
    this->send(String(Protocol::RESP_BOX_CLOSED));
    return;
  }
  
  if(this->boxFullopened)
    if(!this->boxBlocked){
      this->send(String(Protocol::RESP_BOX_OPEN_FULL));
      return;
    }else{
      this->send(String(Protocol::RESP_BOX_BLOCKED));
      return;
    }
  else
    if(!this->boxBlocked){
      this->send(String(Protocol::RESP_BOX_OPEN_HALF));
      return;
    }else{
      this->send(String(Protocol::RESP_BOX_BLOCKED));
      return;
    }
  }

  if(message == String(Protocol::COMMAND_BOX_OPEN)){
    this->boxFullopened = true;
    this->boxClosed = false;
    this->send(String(Protocol::CALLBACK_BOX_WORKING_OPENING));
    delay(2000);  
    this->send(String(Protocol::RESP_BOX_OPEN_FULL));
    return;
  }
  
  if(message == String(Protocol::COMMAND_BOX_CLOSE)){
    this->boxFullopened = false;
    this->boxClosed = true;
    this->send(String(Protocol::CALLBACK_BOX_WORKING_CLOSING));
    delay(2000);
    this->send(String(Protocol::RESP_BOX_CLOSED));
    return;
  }

  /************************
   ****** Keep alive ******
   ************************/
   if(message == String(Protocol::COMMAND_KEEP_ALIVE)){
    this->send(String(Protocol::RESP_SUCCESS));
    return;
   }

   /************************
   ****** Scale ******
   ************************/
   if(message == String(Protocol::COMMAND_SCALE_TURNON)){
    return;
   }
   
   if(message == String(Protocol::COMMAND_SCALE_PRINTP)){
    this->send("+  1.500 Kg");
    return;
   }

   if(message == String(Protocol::COMMAND_SCALE_PRINTI)){
    this->send("+  1.500 Kg");
    return;
   }

   if(message == String(Protocol::COMMAND_CUBOMETER_MEASURES)){
    this->send("[RESP]MEASURES:9x13.5x18x0");
    return;
   }


   this->send(String(Protocol::RESP_UNRECOGNIZED));
}
