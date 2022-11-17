namespace java thrift.generated
namespace py py.thrift.generated

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

// struct关键字用于定义结构体，相当于面向对象编程语言中的类
struct Person {
    // 相当于定义类中的成员，并生成相应的get和set方法，optional表示username这个成员可以没有
    1: optional String username,
    2: optional int age,
    3: optional boolean married
}

// 定义一个异常类型，用于接口中可能抛出的异常
exception DataException {
    1: optional String message,
    2: optional String callStack,
    3: optional String date
}

// 定义服务接口
service PersonService {
    Person getPersonByUsername(1: required String username) throws (1: DataException data)
    void savePerson(1: required Person person)
}
