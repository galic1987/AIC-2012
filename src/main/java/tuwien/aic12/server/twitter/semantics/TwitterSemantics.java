package tuwien.aic12.server.twitter.semantics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tuwien.aic12.server.twitter.semantics.classifier.ClassifierBuilder;
import tuwien.aic12.server.twitter.semantics.classifier.WekaClassifier;
import tuwien.aic12.server.twitter.semantics.util.Options;
import twitter4j.Status;
import weka.classifiers.bayes.NaiveBayes;

/**
 *
 * @author vanjalee
 */
public class TwitterSemantics {

    private Double formatValue(Double toFormat) {
        String sd = toFormat.toString();
        if(sd.length() > 4) {
            return Double.valueOf(sd.substring(0, 4));
        }
        return toFormat;
    }

    public Double analyse(List<Status> tweets) {
        try {
            ClassifierBuilder clb = new ClassifierBuilder();
            Options opt = new Options();
            clb.setOpt(opt);
            opt.setSelectedFeaturesByFrequency(true);
            opt.setNumFeatures(200);
            opt.setRemoveEmoticons(true);
            clb.prepareTrain();
            clb.prepareTest();
            NaiveBayes nb = new NaiveBayes();
            WekaClassifier wc = clb.constructClassifier(nb);
            List<String> results = new ArrayList<>();
            wc.evaluate();
            for (Status t : tweets) {
                String result = wc.classify(t.getText());
                results.add(result);
            }
            Double estimation = convertResults(results);
            estimation = formatValue(estimation);
            System.out.println("Estimation : " + estimation);
            return estimation;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TwitterSemantics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TwitterSemantics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TwitterSemantics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Double convertResults(List<String> stringResults) {
        double zero = 0;
        double four = 0;
        /*
        double one = 0;
        double two = 0;        
        double three = 0;
        */        
        double size = stringResults.size();
        for (String s : stringResults) {
            if (s.equals("0")) {
                ++zero;            
            /*    
            } else if (s.equals("1")) {
                ++one;            
            } else if (s.equals("2")) {
                ++two;            
            } else if (s.equals("3")) {
                ++three;
            */    
            } else {
                ++four;
            }
        }        
        System.out.println("Zero : " + zero);
        System.out.println("Four : " + four);
        /*
        System.out.println("One : " + one);        
        System.out.println("Two : " + two);
        System.out.println("Three : " + three);
        */        
        double zeroD = zero / size;
        double fourD = four / size;
        /*
        double oneD = one / size;
        double twoD = two / size;
        double threeD = three / size;
        */        
        System.out.println("Zero : " + zeroD);
        System.out.println("Four : " + fourD);
        /*
        System.out.println("One : " + oneD);        
        System.out.println("Two : " + twoD);
        System.out.println("Three : " + threeD);
        */        
        return fourD;
    }
}
