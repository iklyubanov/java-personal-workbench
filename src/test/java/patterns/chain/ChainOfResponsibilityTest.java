package patterns.chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

public class ChainOfResponsibilityTest {

    private ManagerPPower manager;
    private DirectorPPower director;
    private VicePresidentPPower vp;
    private PresidentPPower president;

    @Before
    public void init() {
        manager = new ManagerPPower();
        director = new DirectorPPower();
        vp = new VicePresidentPPower();
        president = new PresidentPPower();
        manager.setSuccessor(director);
        director.setSuccessor(vp);
        vp.setSuccessor(president);
    }

    @Test(expected = NoSuchMoneyException.class)
    public void checkAuthority() {
        manager.processRequest(new PurchaseRequest(5000, "General"));
        manager.processRequest(new PurchaseRequest(5001, "General"));
        manager.processRequest(new PurchaseRequest(50001, "Bribe"));
    }
}

abstract class PurchasePower {
    protected static final double BASE = 500;
    protected PurchasePower successor;

    abstract protected double getAllowable();
    abstract protected String getRole();

    public void setSuccessor(PurchasePower successor) {
        this.successor = successor;
    }

    public void processRequest(PurchaseRequest request) {
        if(request.getAmount() <= this.getAllowable()) {
            System.out.println(this.getRole() + " will approve $" + request.getAmount());
        } else if (successor != null) {
            successor.processRequest(request);
        } else {
            throw new NoSuchMoneyException("Sorry! You're requesting $" + request.getAmount() + ". Our company don't have such amount of money!");
        }
    }
}

class ManagerPPower extends PurchasePower {
    @Override
    protected double getAllowable() {
        return BASE * 10;
    }

    @Override
    protected String getRole() {
        return "Manager";
    }
}

class DirectorPPower extends PurchasePower {
    @Override
    protected double getAllowable() {
        return BASE * 20;
    }

    @Override
    protected String getRole() {
        return "Director";
    }
}

class VicePresidentPPower extends PurchasePower {
    @Override
    protected double getAllowable() {
        return BASE * 40;
    }

    @Override
    protected String getRole() {
        return "Vice President";
    }
}

class PresidentPPower extends PurchasePower {
    @Override
    protected double getAllowable() {
        return BASE * 60;
    }

    @Override
    protected String getRole() {
        return "President";
    }
}

@Data
@AllArgsConstructor
class PurchaseRequest {
    private double amount;
    private String purpose;
}

class NoSuchMoneyException extends RuntimeException {
    NoSuchMoneyException(String message) {
        super(message);
        System.out.println(message);
    }
}
