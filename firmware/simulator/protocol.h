#define PROTOCOL_H

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

    static const char* COMMAND_BOX_STATE;
    static const char* COMMAND_BOX_OPEN;
    static const char* COMMAND_BOX_CLOSE;
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
    
             
    static const char* RESP_BOX_CLOSED;
    static const char* RESP_BOX_OPEN_FULL;
    static const char* RESP_BOX_OPEN_HALF;
    static const char* RESP_BOX_BLOCKED;
    static const char* RESP_BOX_WORKING_OPENING;
    static const char* RESP_BOX_WORKING_CLOSING;

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
    static const char* CALLBACK_BOX_WORKING_OPENING;
    static const char* CALLBACK_BOX_WORKING_CLOSING;
    static const char* CALLBACK_UNRECOGNIZED_COMM;

    /******** Other simulations **********/
    static const char* COMMAND_SCALE_TURNON;
    static const char* COMMAND_SCALE_PRINTP;
    static const char* COMMAND_SCALE_PRINTI;
    
    static const char* COMMAND_CUBOMETER_MEASURES;


    boolean boxFullopened = false;
    boolean boxClosed = false;
    boolean boxBlocked = false;
  
    /******** Methods ***********/
    void communicate(void);
    void receive(String command);
    void send(String message);
 };

