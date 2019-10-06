import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;


public class Sender extends Agent {

    public static void Sending(String receiver, ACLMessage msg, AID aid, Agent agent) {
        msg.addReceiver(aid);
        msg.setContent("Hello, "+ receiver);
        msg.setProtocol("P1");
        agent.send(msg);
        msg.setContent("You are suck, "+ receiver);
        msg.setProtocol("P2");
        agent.send(msg);
    }
    @Override
    protected void setup() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage msg1_1 = new ACLMessage(ACLMessage.INFORM), msg2_1 = new ACLMessage(ACLMessage.INFORM);
                AID aid1 = new AID("Receiver1",false); AID aid2 = new AID("Receiver2",false);
                Sending("Receiver1",msg1_1,aid1,getAgent());
                Sending("Receiver2",msg2_1,aid2,getAgent());
            }
        });
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println(" - " +
                            myAgent.getLocalName() + " получил письмо от " +
                            msg.getContent());
                }
                block();
            }
        });
    }
}
