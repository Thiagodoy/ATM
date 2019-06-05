#define DISPENSER_H

/********* State machines ********/
//DISPENSER
// actionCommand > endSensor = HIGH > motor = HIGH > motorTimeout > removeSensor = HIGH > motor = LOW > removeTimeout > removeSensor = LOW > endSensor = HIGH > SUCCESS;
// actionCommand > endSensor = HIGH > motor = HIGH > motorTimeout > removeSensor = HIGH > motor = LOW > removeTimeout > removeSensor = LOW > endSensor = LOW > WARNING;
// actionCommand > endSensor = HIGH > motor = HIGH > motorTimeout > removeSensor = HIGH > motor = LOW > removeTimeout > removeSensor = HIGH > ERROR:notRemoved;
// actionCommand > endSensor = HIGH > motor = HIGH > motorTimeout > removeSensor = LOW > ERROR:blocked;
// actionCommand > endSensor = LOW > ERROR:empty;

/********** Structs  *************/
class Dispenser {
  private:
    int id;
  //mode to set the relay (0 = normal; 1 = invert)
    byte relayMode;
  //Effectors
    int injectorMotorDO; //motor to inject envelop
  
  //Sensors
    int remotionObstacleDI; //sensor to detect the remotion of package
    int emptyObstacleDI; //sensor to detect the end of packages

  //parameters
    int capacity; //total capacity
    int throughput; //number of packages to warn
    int motorTimeout; //time while motor is active
    int removeTimeout; //time that user must remove package before warning

    void (*workingCallback)(Dispenser *dispenser);
  //States
    int counter = 0; //package counter
    int state = 0;    // 0 = OK; 1 = WARN:EMPTY; 2 = WARN:LOW_LEVEL; 3 = ERROR:BLOCKED
    long lastDispensation = 0L; // time of last dispensation

    static const int MAX_DISPENSATION_ATTEMPS = 3;

  //Methods (private)
  bool isEmpty();
  bool isBlocked();
  void turnOnMotor();
  void turnOffMotor();
  public:
  //State codes
    static const int CODE_IDLE = 0;
    static const int CODE_WORKING = 1;
    static const int CODE_READY_TO_REMOVE = 2;
    static const int CODE_WARN_EMPTY = 3;
    static const int CODE_WARN_LOW_LEVEL = 4;
    static const int CODE_ERROR_NOT_DISPENSED = 5;
    static const int CODE_ERROR_BLOCKED = 6;
    static const int CODE_FAIL = 0xFF;
  
    Dispenser(int pId, byte pRelayMode, int pInjectorMotorDO, int pRemotionObstacleDI, int pEmptyObstacleDI);
    void setup(void);
    void configure(int pCapacity, int pThroughput, int pMotorTimeout, int pRemoveTimeout);
    int dispense(void);
    int getState(void);
    int getId(void);
    void setCounter(int pCounter);
    int getCounter(void);
    void setWorkingCallback(void (*pCallback));
};
