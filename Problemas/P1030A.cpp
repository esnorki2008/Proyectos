void P1030A();
int main()
{
    P1030A();
    return 0;
}
void P1030A() {
    int Number,In;
    std::cin >> Number;
    bool Ez=1;
    for (int i = 0;i < Number;i++) {
        std::cin >> In;
        if (In == 1)
            Ez = 0;
    }
    if (Ez == 0)
        std::cout << "HARD";
    else
        std::cout << "EASY";
}