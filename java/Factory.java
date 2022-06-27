package java;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Factory {
    public int calculate(int a, int b, String operator) {
        int result = Integer.MIN_VALUE;

        if ("add".equals(operator)) {
            result = a + b;
        } else if ("multiply".equals(operator)) {
            result = a * b;
        } else if ("divide".equals(operator)) {
            result = a / b;
        } else if ("subtract".equals(operator)) {
            result = a - b;
        }
        return result;
    }
}

/**
 * Below is what called Factory Design Pattern
 */
interface Operation {
    int apply(int a, int b);
}

class Addition implements Operation {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}

class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put("add", new Addition());
        // operationMap.put("divide", new Division());
    }

    public static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }

    public int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory.getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }
}
