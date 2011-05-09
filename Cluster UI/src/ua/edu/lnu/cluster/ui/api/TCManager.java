package ua.edu.lnu.cluster.ui.api;

import java.util.HashMap;
import java.util.Map;
import ua.edu.lnu.cluster.DataModel;
import ua.edu.lnu.cluster.ui.RawDataUITopComponent;
import ua.edu.lnu.cluster.ui.dm.DataModelTopComponent;

/**
 * 
 * @author pif
 */
public class TCManager {

    private static TCManager instance;
    private Map<DataModel, DataModelTopComponent> openedModels = new HashMap<DataModel, DataModelTopComponent>();
//        private Map<DataSet, RawDataUITopComponent> openedDataSets = new HashMap<DataSet, RawDataUITopComponent>();

    public static TCManager getInstance() {
        if (instance == null) {
            instance = new TCManager();
        }
        return instance;
    }

    private TCManager() {
    }

    public void openDataModelWindow(DataModel model) {
        DataModelTopComponent modelTopComponent = null;

        if (openedModels.containsKey(model)) {
            modelTopComponent = openedModels.get(model);
            modelTopComponent.requestActive();
        } else {
            modelTopComponent = new DataModelTopComponent(model); // otherwise create new window to open network in
            modelTopComponent.open();
            openedModels.put(model, modelTopComponent);
            modelTopComponent.requestActive();
        }
    }

    public void onDataModelClose(DataModel nnet) {
        openedModels.remove(nnet);
    }
    /**
     *  Opens TrainigSetEditFrameTopComponent - opened by double clicking on training set
     * @param trainingSet - input trainig set that will be edited
     */
//    public void openTrainingSetWindow(TrainingSet trainingSet) {
//        TrainingSetTopComponent trainingSetTopComponent = null;
//
//        if (openedTrainingSets.containsKey(trainingSet)) {
//            trainingSetTopComponent = openedTrainingSets.get(trainingSet); // if network is allready opened get the window
//            trainingSetTopComponent.requestActive();
//        } else {
//            trainingSetTopComponent = new TrainingSetTopComponent(trainingSet); // otherwise create new window to open network in
//            //  trainingSetTopComponent.setTrainingSetEditFrameVariables(trainingSet);
//            trainingSetTopComponent.open();
//            openedTrainingSets.put(trainingSet, trainingSetTopComponent);
//            trainingSetTopComponent.requestActive();
//        }
//
//    }
}