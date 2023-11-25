package com.dga.equiz.model.loading;

import com.dga.equiz.model.event.IEvent;
import com.dga.equiz.model.nodeObject.NodeObject;
import com.dga.equiz.utils.EquizUtils;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class LoadScene {
    public NodeObject loadObject;
    public LoadingTask loadingTask;
    public IEvent loadEvent;

    public LoadScene(Pane parent, NodeObject targetScene, IEvent loadEvent) {
        this.loadEvent = loadEvent;

        try {
            this.loadObject = EquizUtils.Instantiate("/view/campaign/CampaignPickerView.fxml", parent);
            this.loadObject.show();
            this.loadingTask = new LoadingTask(loadEvent);
            this.loadingTask.setOnSucceeded(workerStateEvent -> {
                this.loadObject.hide();
                targetScene.show();
            });
            System.out.println("Init load scene done!");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void startLoad() {
        System.out.println("Start load thread!");
        new Thread(this.loadingTask).start();
        System.out.println("End load thread!");
    }
}
