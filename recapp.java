import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;   //to load dataset from a csv file
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;  // to find nearest N users
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender; //generate recommendations
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;  // comparison using pearson correlation
import org.apache.mahout.cf.taste.model.DataModel; // represent user-item-rating data
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem; // item and score as output
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity; //  to measure user-to-user similarity

import java.io.File;//to load dataset from a file 
import java.util.List; //to store results in a list

public class recapp {
    public static void main(String[] args) {
        System.out.println("✅ Program started.");
        try {             //to ensure if anything goes wrong and to handle exceptions
            DataModel model = new FileDataModel(new File("dataset.csv"));  //reads data from your  csv file
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);   //pearson correlation to compare users
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model); //defines neighbourhood
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); //recommender
            List<RecommendedItem> recommendations = recommender.recommend(1, 3); //how many recommendations for given user id

            System.out.println("Recommendations for User 1:"); //prints the recommendations
            for (RecommendedItem item : recommendations) {
                System.out.printf("Item ID: %d, Score: %.2f\n", item.getItemID(), item.getValue());
            }

        } catch (Exception e) { //to handle exceptions if any
            e.printStackTrace();
        }
    }
}
