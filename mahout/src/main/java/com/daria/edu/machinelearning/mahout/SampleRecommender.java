package com.daria.edu.machinelearning.mahout;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Test program Example from
 * https://mahout.apache.org/users/recommender/userbased-5-minutes.html
 */

public class SampleRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		DataModel dataModel = new FileDataModel(new File("data/dataset.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1,
				similarity, dataModel);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(
				dataModel, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
	}

}
