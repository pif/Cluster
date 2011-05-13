/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.upgma;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Taras
 */
public class ClusterTree {

    
    
    public static class Node {

        private double[] observation;
        private List<ClusterTree.Node> children;

        public Node(List<ClusterTree.Node> children) {
            this.children = children;
        }

        public Node(double[] observation) {
            this.observation = observation;
            this.children = Collections.EMPTY_LIST;
        }

        public double[] getObservation() {
            return observation;
        }

        public void setObservation(double[] observation) {
            if (observation == null) {
                throw new NullPointerException("Observation is null!");
            }
            this.observation = observation;
        }

        public List<Node> getChildren() {
            return Collections.unmodifiableList(children);
        }

        public void setChildren(List<Node> children) {
            if (children == null) {
                throw new NullPointerException("Children list is null!");
            }
            this.children = children;
        }
    }
}
