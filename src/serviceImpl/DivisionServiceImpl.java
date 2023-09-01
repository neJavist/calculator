package serviceImpl;

import service.CalculateService;

public class DivisionServiceImpl implements CalculateService {
    @Override
    public int calculate(int firstNumber, int secondNumber) {
        return firstNumber / secondNumber;
    }
}
