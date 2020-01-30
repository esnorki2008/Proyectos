void P617A();

int main()
{
    P617A();
    return 0;
}

void P617A() {
    int Input;
    std::cin >> Input;
    int Out = Input / 5;
    if (Input % 5 != 0)
        Out++;

        std::cout << Out;
}