#include "protocol.h"

Protocol protocol;

void setup() {
   Serial.begin(9600);
   serialFlush();
}

void loop() {
  protocol.communicate();
  delay(50);    
}

void serialFlush(void){
  while(Serial.available() > 0)
    Serial.read();
}
