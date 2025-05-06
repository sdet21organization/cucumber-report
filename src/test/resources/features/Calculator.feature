Feature: Calculator
  Данный фича файл содержит сценарии использования калькулятора


  @fast
  @PROJECT-1234-1
  Scenario: Addition
  Тест на проверку сложения
    Given I opened a calculator
    When I add 2 and 1
    And print string "something"
    Then the result should be 4

  Scenario Outline: Parametrized. Add two numbers <num1> & <num2>
    Given I opened a calculator
    When I add <num1> and <num2>
    Then the result should be <total>

    Examples:
      | num1 | num2 | total |
      | -2   | 3    | 1     |
      | 10   | 15   | 25    |
      | 99   | -99  | 0     |
      | -1   | -10  | -11   |


  Scenario: Addition negative number
  Тест на проверку сложения отрицательных чисел
    Given I opened a calculator
    When I add 2 and -2
    Then the result should be 0

  Scenario: Substraction
  Тест на проверку вычитания
    Given I opened a calculator
    When from 2 I substract 2
    Then the result should be 0

  Scenario: Multiply
  Тест на проверку умножения
    Given I opened a calculator
    When I multiply 2 and 3
    Then the result should be 6

  Scenario: Multiply negative
  Тест на проверку умножения отрицательных чисел
    Given I opened a calculator
    When I multiply 2 and -7
    Then the result should be -14