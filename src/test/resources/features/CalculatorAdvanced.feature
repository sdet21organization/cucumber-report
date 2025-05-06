Feature: Advanced Calculator Operations
  Данный фича файл содержит сценарии для расширенных операций калькулятора

  Scenario: Division
  Тест на проверку деления
    Given I opened a calculator
    When I divide 10 by 2
    Then the result should be 5

  Scenario Outline: Parametrized. Divide <num1> by <num2>
    Given I opened a calculator
    When I divide <num1> by <num2>
    Then the result should be <result>

    Examples:
      | num1 | num2 | result |
      | 6    | 2    | 3      |
      | 10   | 5    | 2      |
      | 9    | 3    | 3      |
      | 10   | 0    | NaN    |

  Scenario: Multiplication
  Тест на проверку умножения
    Given I opened a calculator
    When I multiply 3 and 3
    Then the result should be 9

  Scenario Outline: Parametrized. Multiply <num1> and <num2>
    Given I opened a calculator
    When I multiply <num1> and <num2>
    Then the result should be <result>

    Examples:
      | num1 | num2 | result |
      | 2    | 3    | 6      |
      | -1   | 5    | -5     |
      | 0    | 100  | 0      |
      | 7    | -7   | -49    |
