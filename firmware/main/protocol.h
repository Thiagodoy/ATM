#define PROTOCOL_H

#ifndef ALARM_H
  #include "alarm.h"
#endif

#ifndef SCALE_H
  #include "scale.h"
#endif

#ifndef DISPENSER_H
  #include "dispenser.h"
#endif

#ifndef BOX_H
  #include "box.h"
#endif

/* *****************************************
 * Hardware communication protocol
 * *****************************************
 * 
 /******** Commands **********
static const char* Protocol::COMMAND_DISPENSE_D1     = "DISPENSE_D1";
static const char* Protocol::COMMAND_DISPENSE_D2     = "DISPENSE_D2";
static const char* Protocol::COMMAND_DISPENSE_D3     = "DISPENSE_D3";
static const char* Protocol::COMMAND_DISPENSE_D4     = "DISPENSE_D4";

static const char* Protocol::COMMAND_RECHARGE_D1     = "RECHARGE_D1:";
static const char* Protocol::COMMAND_RECHARGE_D2     = "RECHARGE_D2:";
static const char* Protocol::COMMAND_RECHARGE_D3     = "RECHARGE_D3:";
static const char* Protocol::COMMAND_RECHARGE_D4     = "RECHARGE_D4:";
                   
static const char* Protocol::COMMAND_STATE_D1        = "STATE_D1";
static const char* Protocol::COMMAND_STATE_D2        = "STATE_D2";
static const char* Protocol::COMMAND_STATE_D3        = "STATE_D3";
static const char* Protocol::COMMAND_STATE_D4        = "STATE_D4";
                   
static const char* Protocol::COMMAND_STATE_BOX       = "STATE_BOX";
static const char* Protocol::COMMAND_OPENBOX         = "OPENBOX";
static const char* Protocol::COMMAND_CLOSEBOX        = "CLOSEBOX";
static const char* Protocol::COMMAND_WEIGHT          = "WEIGHT";
                   
static const char* Protocol::COMMAND_ALARM_RED       = "ALARM_RED";
static const char* Protocol::COMMAND_ALARM_YELLOW    = "ALARM_YELLOW";
static const char* Protocol::COMMAND_ALARM_OFF       = "ALARM_OFF";
                   
static const char* Protocol::COMMAND_FULLSTATE       = "FULLSTATE";
                   
/******** ResponsesProtocol:: *********
static const char* Protocol::RESP_SUCCESS               = "[RESP]SUCCESS";                                
static const char* Protocol::RESP_FAIL                  = "[RESP]FAIL";
                   
static const char* Protocol::RESP_WARNING_EMPTY_D       = "[RESP]WARNING_EMPTY_D";
static const char* Protocol::RESP_WARNING_LOW_LEVEL_D   = "[RESP]WARNING_LOW_LEVEL_D";
static const char* Protocol::RESP_ERROR_BLOCKED_D       = "[RESP]ERROR_BLOCKED_D";  
                   
static const char* Protocol::RESP_NOT_OPENED            = "[RESP]NOT_OPENED";   
static const char* Protocol::RESP_NOT_CLOSED            = "[RESP]NOT_CLOSED";         
static const char* Protocol::RESP_WEIGHT                = "[RESP]WEIGHT:";
static const char* Protocol::RESP_SCALE_OFFLINE         = "[RESP]SCALE_OFFLINE";
                   
/******** CallbacksProtocol:: *********
static const char* Protocol::CALLBACK_FAIL                 = "[CB]FAIL";
static const char* Protocol::CALLBACK_SCALE_OFFLINE        = "[CB]SCALE_OFFLINE";
static const char* Protocol::CALLBACK_WARNING_EMPTY_D      = "[CB]CALLBACK_WARNING_EMPTY_D";
static const char* Protocol::CALLBACK_WARNING_LOW_LEVEL_D  = "[CB]CALLBACK_WARNING_LOW_LEVEL_D";
static const char* Protocol::CALLBACK_ERROR_BLOCKED_D      = "[CB]CALLBACK_ERROR_BLOCKED_D";
static const char* Protocol::CALLBACK_UNRECOGNIZED_COMM    = "[CB]UNRECOGNIZED_COMM";

 * 
 */

 class Protocol{
  private:
    static const int MAX_COMMAND_LENGHT = 100;
    
    Alarm *alarm;
    Scale *scale;
    Dispenser *dispenser1;
    Box *box;
    
  public:
    /******** Commands ***********/
    static const char* COMMAND_STATE_D1;
    static const char* COMMAND_STATE_D2;
    static const char* COMMAND_STATE_D3;
    static const char* COMMAND_STATE_D4;
    
    static const char* COMMAND_INVENTORY_D1;
    static const char* COMMAND_INVENTORY_D2;
    static const char* COMMAND_INVENTORY_D3;
    static const char* COMMAND_INVENTORY_D4;

    static const char* COMMAND_DISPENSE_D1; 
    static const char* COMMAND_DISPENSE_D2;
    static const char* COMMAND_DISPENSE_D3;
    static const char* COMMAND_DISPENSE_D4;
                                                        
    static const char* COMMAND_RECHARGE_D1;
    static const char* COMMAND_RECHARGE_D2;
    static const char* COMMAND_RECHARGE_D3;
    static const char* COMMAND_RECHARGE_D4;

    static const char* COMMAND_BOX_P_STATE;
    static const char* COMMAND_BOX_P_OPEN;
    static const char* COMMAND_BOX_P_CLOSE;

    static const char* COMMAND_BOX_M_STATE;
    static const char* COMMAND_BOX_M_OPEN;
    static const char* COMMAND_BOX_M_CLOSE;

    static const char* COMMAND_BOX_G_STATE;
    static const char* COMMAND_BOX_G_OPEN;
    static const char* COMMAND_BOX_G_CLOSE;
    
    static const char* COMMAND_WEIGHT;

    static const char* COMMAND_ALARM_RED;
    static const char* COMMAND_ALARM_YELLOW;
    static const char* COMMAND_ALARM_OFF;

    static const char* COMMAND_FULLSTATE;

    static const char* COMMAND_KEEP_ALIVE;
    
    /******** Responses **********/
    static const char* RESP_SUCCESS;                                              
    static const char* RESP_FAIL;

    static const char* RESP_IDLE_D;
    static const char* RESP_INVENTORY_D;
    static const char* RESP_WORKING_D;
    static const char* RESP_READY_TO_REMOVE_D;
    static const char* RESP_WARNING_EMPTY_D;
    static const char* RESP_WARNING_LOW_LEVEL_D;
    static const char* RESP_ERROR_NOT_DISPENSED_D;
    static const char* RESP_ERROR_BLOCKED_D;
    
             
    static const char* RESP_BOX_P_CLOSED;
    static const char* RESP_BOX_P_OPEN_FULL;
    static const char* RESP_BOX_P_OPEN_HALF;
    static const char* RESP_BOX_P_BLOCKED;
    static const char* RESP_BOX_P_WORKING_OPENING;
    static const char* RESP_BOX_P_WORKING_CLOSING;

    static const char* RESP_BOX_M_CLOSED;
    static const char* RESP_BOX_M_OPEN_FULL;
    static const char* RESP_BOX_M_OPEN_HALF;
    static const char* RESP_BOX_M_BLOCKED;
    static const char* RESP_BOX_M_WORKING_OPENING;
    static const char* RESP_BOX_M_WORKING_CLOSING;

    static const char* RESP_BOX_G_CLOSED;
    static const char* RESP_BOX_G_OPEN_FULL;
    static const char* RESP_BOX_G_OPEN_HALF;
    static const char* RESP_BOX_G_BLOCKED;
    static const char* RESP_BOX_G_WORKING_OPENING;
    static const char* RESP_BOX_G_WORKING_CLOSING;

    static const char* RESP_UNRECOGNIZED;

    /******** Callbacks **********/
    static const char* CALLBACK_FAIL;
    static const char* CALLBACK_SCALE_OFFLINE;
    static const char* CALLBACK_IDLE_D;
    static const char* CALLBACK_WORKING_D;
    static const char* CALLBACK_READY_TO_REMOVE_D;
    static const char* CALLBACK_WARNING_EMPTY_D;
    static const char* CALLBACK_WARNING_LOW_LEVEL_D;
    static const char* CALLBACK_ERROR_BLOCKED_D;
    
    static const char* CALLBACK_BOX_P_WORKING_OPENING;
    static const char* CALLBACK_BOX_P_WORKING_CLOSING;    
    static const char* CALLBACK_BOX_G_WORKING_OPENING;
    static const char* CALLBACK_BOX_G_WORKING_CLOSING;    
    static const char* CALLBACK_BOX_M_WORKING_OPENING;
    static const char* CALLBACK_BOX_M_WORKING_CLOSING;
    
    static const char* CALLBACK_UNRECOGNIZED_COMM;
  
    /******** Methods ***********/

    static void dispenser1WorkingCallback(Dispenser *dispenser);
    
    static void boxPOpeningCallback(int boxState);
    static void boxPClosingCallback(int boxState);    
    static void boxMOpeningCallback(int boxState);
    static void boxMClosingCallback(int boxState);
    static void boxGOpeningCallback(int boxState);
    static void boxGClosingCallback(int boxState);
    
    void communicate(void);
    void receive(String command);
    void send(String message);
    void sendDispenserCallback(int dispenserState, int dispenserId);
    void sendScaleCallback(int scaleState);

    /*
     * Analisar
     */
    void sendBoxCallback();

    static String translateDispenserState(int dispenserState, int dispenserId);
    static String translateBoxState(int boxState);

    void setAlarm(Alarm *pAlarm);
    void setScale(Scale *pScale);
    void setDispenser1(Dispenser *pDispenser1);
    
    void setBoxP(Box *box);
    void setBoxM(Box *box);
    void setBoxG(Box *box);
 };
