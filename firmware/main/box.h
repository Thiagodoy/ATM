#define BOX_H

class Box{
  private:
  //mode to set the relay (0 = normal; 1 = invert)
    byte relayMode;
  //Effectors
    int openerDO;
    int closerDO;
  //Sensors
    int closedDI;
    int openedDI;
    int blockedDI;

  //Private Methods
    bool isFullOpened(void);
    bool isClosed(void);
    bool isBlocked(void);
    void turnOnOpenerMotor(void);
    void turnOffOpenerMotor(void);
    void turnOnCloserMotor(void);
    void turnOffCloserMotor(void);


  //Callbacks
    void (*openingCallback)(int);
    void (*closingCallback)(int);

  //Constants
    static const long TIMEOUT = 30000;

  public:
  //State codes
    static const int CODE_CLOSED = 0;
    static const int CODE_OPEN_FULL = 1;
    static const int CODE_OPEN_HALF = 2;
    static const int CODE_BLOCKED = 3;
    static const int CODE_WORKING_OPENING = 4;
    static const int CODE_WORKING_CLOSING = 5;
    static const int CODE_FAIL = 0xFF;

    Box(byte pRelayMode, int pOpenerDO, int pCloserDO, int pClosedDI, int pOpenedDI, int pBlockedDI);
    void setup(void);
    int getState(void);
    int open(void);
    int close(void);

    void setOpeningCallback(void (*openingCallback));
    void setClosingCallback(void (*closingCallback));
};

