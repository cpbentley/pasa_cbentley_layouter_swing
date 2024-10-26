/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import java.awt.Container;

import javax.swing.JComponent;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.layouter.src4.engine.LayouterEngineReal;
import pasa.cbentley.layouter.src4.engine.LayoutableRect;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.interfaces.I2DReal;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.ctx.LayouterSwingCtx;
import pasa.cbentley.layouter.swing.interfaces.ILayoutableSwing;

/**
 * Subclass of {@link LayouterEngineReal} with Swing related services
 * 
 * @author Charles Bentley
 *
 */
public class LayEngineSwing extends LayouterEngineReal implements ITechLayout {

   private ILayoutableSwing compLay;

   private int              layoutID;

   private LayouterSwingCtx slc;

   protected ILayoutable    parentLayout;

   public LayEngineSwing(LayouterSwingCtx slc, ILayoutableSwing compo, I2DReal real, int layoutID) {
      super(slc, compo, real);
      this.slc = slc;
      this.compLay = compo;
      this.layoutID = layoutID;
   }

   public String toStringName() {
      return getComponent().getName();
   }

   public void setParentLayout(ILayoutable parentLayout) {
      this.parentLayout = parentLayout;
   }

   /**
    * We override to 
    */
   public ILayoutable getLayoutableParent() {
      if (parentLayout == null) {
         Container parent = compLay.getComponent().getParent();
         if (parent instanceof ILayoutable) {
            return (ILayoutable) parent;
         } else {
            throw new IllegalStateException("ILayoutable must be added to a ILayoutable container");
         }
      } else {
         return parentLayout;
      }
   }

   /**
    * 
    * A {@link ILayoutable} that represents the area
    * 
    * <li>{@link ITechLayout#VIEW_STYLE_00_VIEW_FULL}
    * <li>{@link ITechLayout#VIEW_STYLE_01_VIEW_CONTENT_PAD_BORDER}
    * <li>{@link ITechLayout#VIEW_STYLE_02_VIEW_CONTENT_PAD}
    * <li>{@link ITechLayout#VIEW_STYLE_03_VIEW_CONTENT}
    * 
    * And 
    * 
    * <li>{@link ITechLayout#VIEW_STRUCT_00_ALL_VISIBLE}
    * 
    * @param subView
    * @return
    */
   public void buildLayoutableRectX(LayoutableRect copy, int subView, int subArea) {
      if (subView == ITechLayout.VIEW_STRUCT_00_ALL_VISIBLE) {
         //they are all the same
      }
   }

   public JComponent getComponent() {
      return compLay.getComponent();
   }

   public int getFontHeight() {
      return getReal().getFontHeight();
   }

   public int getFontWidth() {
      return getReal().getFontWidth();
   }

   public ILayoutable getLayoutableDelegate(ILayoutable source) {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutable getLayoutableID(int id) {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutable getLayoutableNav(int dir) {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutable getLayoutableEtalon(int etalonType) {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutable getLayoutableViewContext() {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutable getLayoutableViewPort() {
      // TODO Auto-generated method stub
      return null;
   }

   public int getLayoutID() {
      // TODO Auto-generated method stub
      return 0;
   }

   public int getSizePreferredHeight() {
      return getReal().getRealPrefHeight();
   }

   public int getSizePreferredWidth() {
      return getReal().getRealPrefWidth();
   }

   public void setArea(Zer2DArea area) {
      getLay().setArea(area);
   }

   public void repaintLayoutable() {
      compLay.getComponent().repaint();
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, LayEngineSwing.class);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, LayEngineSwing.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
