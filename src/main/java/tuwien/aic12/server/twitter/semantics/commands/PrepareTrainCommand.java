package tuwien.aic12.server.twitter.semantics.commands;

import java.io.IOException;
import tuwien.aic12.server.twitter.semantics.classifier.ClassifierBuilder;

/**
 * class representing command that prepares the classifier's train
 */
public class PrepareTrainCommand implements Command {

    private ClassifierBuilder clb;

    /**
     * @param clb the receiver object
     */
    public PrepareTrainCommand(ClassifierBuilder clb) {
        this.clb = clb;
    }

    /**
     * calls the receiver to execute command
     */
    @Override
    public void execute() {
        try {
            clb.prepareTrain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
