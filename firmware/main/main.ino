#include "logger.h"
#include "alarm.h"
#include "dispenser.h"
#include "scale.h"
#include "box.h"
#include "protocol.h"

#define RELAY_MODE 0

#define D1_EMPTY 22
#define D1_REMOVED 24
#define D1_MOTOR 26

#define D2_EMPTY 28
#define D2_REMOVED 30
#define D2_MOTOR 32

#define ALARM_RED 48
#define ALARM_YELLOW 50

/*
 * Definição das Box P
*/
#define BOX_P_MOTOR_OPEN 47
#define BOX_P_MOTOR_CLOSE 46
#define BOX_P_OPENED 31
#define BOX_P_CLOSED 33
#define BOX_P_BLOCKED 35

/*
 * Definição das Box M
*/
#define BOX_M_MOTOR_OPEN 47
#define BOX_M_MOTOR_CLOSE 46
#define BOX_M_OPENED 31
#define BOX_M_CLOSED 33
#define BOX_M_BLOCKED 35

/*
 * Definição das Box G
*/
#define BOX_G_MOTOR_OPEN 47
#define BOX_G_MOTOR_CLOSE 46
#define BOX_G_OPENED 31
#define BOX_G_CLOSED 33
#define BOX_G_BLOCKED 35

Alarm alarm(RELAY_MODE, ALARM_RED, ALARM_YELLOW);
Dispenser dispenser1(1, RELAY_MODE, D1_MOTOR, D1_REMOVED, D1_EMPTY);
Dispenser dispenser2(2, RELAY_MODE, D2_MOTOR, D2_REMOVED, D2_EMPTY);

/*
 * Definição das boxs
 */
Box boxP(RELAY_MODE, BOX_P_MOTOR_OPEN, BOX_P_MOTOR_CLOSE, BOX_P_CLOSED, BOX_P_OPENED, BOX_P_BLOCKED);
Box boxM(RELAY_MODE, BOX_M_MOTOR_OPEN, BOX_M_MOTOR_CLOSE, BOX_M_CLOSED, BOX_M_OPENED, BOX_M_BLOCKED);
Box boxG(RELAY_MODE, BOX_G_MOTOR_OPEN, BOX_G_MOTOR_CLOSE, BOX_G_CLOSED, BOX_G_OPENED, BOX_G_BLOCKED);

Protocol protocol;

void setup() {
  Logger::setLevel(Logger::DEBUG_LEVEL);
  
  Serial.begin(9600);
  serialFlush();

  alarm.setup();
  Logger::debug("Alarme set up");
  
  dispenser1.setup();
  Logger::debug("Dispenser set up");
  dispenser1.configure(20,5,2000,5000);
  Logger::debug("Dispenser configured");

  boxP.setup();
  boxM.setup();
  boxG.setup();
  Logger::debug("Boxs set up");

  protocol.setDispenser1(&dispenser1);
  protocol.setAlarm(&alarm);
  
  protocol.setBoxP(&boxP);
  protocol.setBoxM(&boxM);
  protocol.setBoxG(&boxG);
  
  Logger::debug("Protocol configured");

  Logger::debug("Relay mode: " + String(RELAY_MODE));
  if(RELAY_MODE == 1){
    digitalWrite(D1_MOTOR, HIGH); //start with HIGH in motor relay
    digitalWrite(ALARM_RED, HIGH); //start with HIGH in alarm red light
    digitalWrite(ALARM_YELLOW, HIGH); //start with HIGH in alarm yellow light
    digitalWrite(BOX_P_MOTOR_OPEN, HIGH); //start with HIGH in alarm red light
    digitalWrite(BOX_P_MOTOR_CLOSE, HIGH); //start with HIGH in alarm yellow light
    digitalWrite(BOX_M_MOTOR_OPEN, HIGH); //start with HIGH in alarm red light
    digitalWrite(BOX_M_MOTOR_CLOSE, HIGH); //start with HIGH in alarm yellow light
    digitalWrite(BOX_G_MOTOR_OPEN, HIGH); //start with HIGH in alarm red light
    digitalWrite(BOX_G_MOTOR_CLOSE, HIGH); //start with HIGH in alarm yellow light
  }
}

bool show = true;
void loop() {
  
  protocol.communicate();

  bool isTime = ((millis() / 3000) % 2) == 0;
  if(isTime){
    if(show){
      int stateD1 = dispenser1.getState();
      protocol.sendDispenserCallback(stateD1,1);
      show = false;
    }else
      delay(50);    
  }else
    show = true;
}

void serialFlush(void){
  while(Serial.available() > 0)
    Serial.read();
}
