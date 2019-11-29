/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JScrollPane;

import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.widgets.BLPanel;
import pasa.cbentley.layouter.swing.widgets.LayoutableAdapter;

/**
 * Layout for a root
 * @author Charles Bentley
 *
 */
public class LayouterLayout implements LayoutManager {

   protected final SwingLayouterCtx slc;

   public LayouterLayout(SwingLayouterCtx slc) {
      this.slc = slc;
   }

   public void addLayoutComponent(String name, Component comp) {
      //not called with this layout
   }

   public void removeLayoutComponent(Component comp) {
   }

   /**
    * 
    */
   public Dimension preferredLayoutSize(Container parent) {
      //at minimum we take the whole parent
      Insets insets = parent.getInsets();
      int width = parent.getWidth() - (insets.left + insets.right);
      int height = parent.getHeight() - (insets.top + insets.bottom);

      //compute size

      Dimension dim = new Dimension(width, height);
      return dim;
   }

   public Dimension minimumLayoutSize(Container parent) {
      return parent.getMinimumSize();
   }

   /**
    * TODO a layoutable must track its dependencies so that when it is updated,
    * only those dependencies are updated and not to whole graph
    * 
    */
   public void layoutContainer(Container container) {
      BLPanel blPanel = (BLPanel) container;
      
      BLPanel panel = (BLPanel) blPanel;
      panel.layoutUpdateStart(); //invalidates the layouts

      
      Insets insets = blPanel.getInsets();
      int maxWidth = blPanel.getWidth() - (insets.left + insets.right);
      int maxHeight = blPanel.getHeight() - (insets.top + insets.bottom);
      int nComps = blPanel.getComponentCount();
   
      for (int i = 0; i < nComps; i++) {
         Component c = blPanel.getComponent(i);
         ILayoutable layoutable = null;
         if (c instanceof ILayoutable) {
            layoutable = (ILayoutable) c;
         } else {
            layoutable = blPanel.getLayoutAdapterFor(c);
         }
         
         //first compute the sizes
         layoutable.layoutUpdateSizeCheck();
         //position it if not already done it
         layoutable.layoutUpdatePositionCheck();
     
        
      }
      
   }

}
