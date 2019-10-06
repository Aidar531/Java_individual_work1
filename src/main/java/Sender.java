import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Sender extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage msg1= new ACLMessage(ACLMessage.INFORM), msg2 = new ACLMessage(ACLMessage.INFORM);
                AID aid = new AID("Receiver1",false);
                msg1.addReceiver(aid);
                msg1.setContent("Hello, Smith");
                msg1.setProtocol("energy");
                getAgent().send(msg1);
            }
        });
    }
}
