#include <iostream>
void P977A();
int P977A(int Num, int Time);




int main()
{
    P977A();
    return 0;
}


void P977A() {

    int Number,Times;
    std::cin >> Number;
    std::cin >> Times;
   std::cout<< P977A(Number,Times);
}

int P977A(int Num,int Time) {
    if (Time == 0)
        return Num;
    if (Num % 10 == 0)
        Num = Num / 10;
    else
        Num = Num--;
    return P977A(Num,Time-1);
}