/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;

/**
 * {@link LayoutManager} for a {@link JPanelLayoutable}
 * 
 * @author Charles Bentley
 *
 */
public class LayoutManagerForLayouter implements LayoutManager, IStringable {

   protected final SwingLayouterCtx slc;

   public LayoutManagerForLayouter(SwingLayouterCtx slc) {
      this.slc = slc;
   }

   public void addLayoutComponent(String name, Component comp) {
      //not called with this layout
      throw new RuntimeException();
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
      JPanelLayoutable panelLayoutable = (JPanelLayoutable) container;

      panelLayoutable.layoutUpdateStart(); //invalidates the layouts

      Insets insets = panelLayoutable.getInsets();
      int maxWidth = panelLayoutable.getWidth() - (insets.left + insets.right);
      int maxHeight = panelLayoutable.getHeight() - (insets.top + insets.bottom);
      int nComps = panelLayoutable.getComponentCount();

      for (int i = 0; i < nComps; i++) {
         Component component = panelLayoutable.getComponent(i);

         ILayoutable layoutable = null;
         if (component instanceof ILayoutable) {
            //we have a Swing component that implements ILayoutable
            layoutable = (ILayoutable) component;
         } else {
            //we don't.. we need to get the adapter that does
            layoutable = panelLayoutable.getLayoutAdapterFor(component);
         }

         //first compute the sizes
         layoutable.layoutUpdateSizeCheck();
         //position it if not already done it
         layoutable.layoutUpdatePositionCheck();

      }

   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, LayoutManagerForLayouter.class);
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, LayoutManagerForLayouter.class);
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return slc.getUC();
   }

   //#enddebug

}
