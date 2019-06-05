#define SCALE_H

class Scale{
  private:
  public:
    static const int Scale::CODE_OK = 0;
    static const int Scale::CODE_SCALE_OFFLINE = 1;
    static const int Scale::CODE_FAIL = 0xFF;

    void setup(void);
    float getWeight(void);
    bool isOnline(void);
};

