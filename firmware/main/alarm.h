#define ALARM_H

class Alarm {
  private:
  //mode to set the relay (0 = normal; 1 = invert)
    byte relayMode;
  //effectors
    int redDO;
    int yellowDO;
  public:
    Alarm(byte pRelayMode, int pRedDO, int pYellowDO);
    void setup();
    void turnOnRed();
    void turnOffRed();
    void turnOnYellow();
    void turnOffYellow();
    int getStatusRed();
    int getStatusYellow();
};
