#include <iostream>
using namespace std;

// ------------- Function templates ------------
template <class T>
T GetMax(T a, T b) {
    return a > b ? a : b;
}

// ------------- Class templates ---------------
template <class T>
class Pair {
    T value[2];
  public: 
    Pair(T a, T b) {
        value[0] = a;
        value[1] = b;
    }
    T getMax();
};

template <class T>
T Pair<T>::getMax() { 
    return a > b ? a : b;
}

// ------------- Template specialization --------------
template <class T> 
class AClass {
    T element;
  public:
    AClass (T arg) {
        element = arg;
    }
    T increase() {
        ++element;
    }
};

template <>
class AClass <char> {
    char element;
  public:
    AClass (char arg) {
        element = arg;
    }
    char uppercase() {
        if ((element > 'a') && element <= 'z') {
            element += 'A' - 'a';
        }
        return element;
    }
};

// ---------- Non-type parameters for templates -----------
template <class T, int N>
class BClass {
    T data[N];
  public:
      void setter(int index, T value);
      T getter(int index);
};

template <class T, int N>
void BClass<T, N>::setter(int index, T value) {
    data[index] = value;
}

template <class T, int N>
T BClass<T, N>::getter(int index) {
    return data[index];
}

// ------------- unit testing ---------------
int main(int argc, char *argv[])
{
    int x = GetMax(1, 2);
    int y = GetMax<int> (1, 2);

    Pair<int> mPair(1, 1);
    Pair<double> mPair2(1.0, 20.1);
    int tmp = mPair.getMax();

    AClass<int> object1(1);
    AClass<char> object2('a');

    return 0;
}