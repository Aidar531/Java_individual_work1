import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.sound.midi.Receiver;

public class Receiver2 extends Agent
{
    protected void setup()
    {
        addBehaviour(new CyclicBehaviour(this)
        {
            public void action()
            {
                MessageTemplate template = MessageTemplate.MatchProtocol("P2");
                ACLMessage msg = receive(template);
                if (msg!=null) {
                    System.out.println(" - " +
                            myAgent.getLocalName() + " <- " +
                            msg.getContent());
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative( ACLMessage.INFORM );
                    reply.setProtocol("P2");
                    reply.setContent(" go away " );
                    getAgent().send(reply);
                }
                block();
            }
        });
    }
}
