#define LOGGER_H

class Logger{
  private:
    static int level;
  public:
    static const int ERROR_LEVEL;
    static const int WARN_LEVEL;
    static const int INFO_LEVEL;
    static const int DEBUG_LEVEL;

    static void error(String message);
    static void warn(String message);
    static void info(String message);
    static void debug(String message);
  
    static void setLevel(int level);
    static void setInactive(void);
};

