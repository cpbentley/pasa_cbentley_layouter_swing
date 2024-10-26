/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import javax.swing.JComponent;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.layouter.src4.ctx.ObjectLC;
import pasa.cbentley.layouter.src4.interfaces.I2DReal;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.ctx.ObjectSwingLayouter;
import pasa.cbentley.layouter.swing.ctx.LayouterSwingCtx;

/**
 * Encapsulate the {@link JComponent} and implements {@link I2DReal} interface
 * 
 * TODO parent 2dreal ?
 * 
 * @author Charles Bentley
 *
 */
public class JComponentReal extends ObjectSwingLayouter implements I2DReal {

   protected final JComponent component;

   public JComponentReal(LayouterSwingCtx slc, JComponent component) {
      super(slc);
      this.component = component;

      slc.toStringCheckNull(component);
   }

   /**
    * {@link I2DReal#getFontHeight()}
    */
   public int getFontHeight() {
      Font font = component.getFont();
      FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
      return fontMetrics.getHeight();
   }

   /**
    * {@link I2DReal#getFontWidth()}
    */
   public int getFontWidth() {
      Font font = component.getFont();
      FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
      return fontMetrics.stringWidth("m");
   }

   public String getName() {
      return component.getName();
   }

   public I2DReal getParent2D() {
      // TODO Auto-generated method stub
      return null;
   }

   public int getRealHeight() {
      return component.getHeight();
   }

   /**
    * Defined by {@link ILayoutable#getSizePropertyValueH(int)}
    * @param property
    * @return
    */
   public int getSizePropertyValueH(int property) {
      switch (property) {
         case ITechLayout.SIZER_PROP_00_DRAWN:
            return getRealHeight();
         case ITechLayout.SIZER_PROP_01_PREFERRED:
            return getRealPrefHeight();
         case ITechLayout.SIZER_PROP_03_FONT:
            return getFontHeight();
         case ITechLayout.SIZER_PROP_05_CONTENT:
            return getRealHeight();
         case ITechLayout.SIZER_PROP_06_CONTENT_PAD:
            return getRealHeight();
         case ITechLayout.SIZER_PROP_07_CONTENT_PAD_BORDER:
            return getRealHeight();
         case ITechLayout.SIZER_PROP_10_PAD:
            return getRealHeight();
         default:
            break;
      }
      return 0;
   }

   /**
    * Defined by {@link ILayoutable#getSizePropertyValueW(int)}
    * @param property
    * @return
    */
   public int getSizePropertyValueW(int property) {
      switch (property) {
         case ITechLayout.SIZER_PROP_00_DRAWN:
            return getRealWidth();
         case ITechLayout.SIZER_PROP_01_PREFERRED:
            return getRealPrefWidth();
         case ITechLayout.SIZER_PROP_03_FONT:
            return getFontWidth();
         case ITechLayout.SIZER_PROP_05_CONTENT:
            return getRealWidth();
         case ITechLayout.SIZER_PROP_06_CONTENT_PAD:
            return getRealWidth();
         case ITechLayout.SIZER_PROP_07_CONTENT_PAD_BORDER:
            return getRealWidth();
         case ITechLayout.SIZER_PROP_10_PAD:
            return getRealWidth();
         default:
            break;
      }
      return 0;
   }

   public int getRealPrefHeight() {
      return component.getPreferredSize().height;
   }

   public int getRealPrefWidth() {
      return component.getPreferredSize().width;
   }

   public int getRealWidth() {
      return component.getWidth();
   }

   public void repaint() {
      component.repaint();
   }

   public void setLocation(int x, int y) {
      component.setLocation(x, y);
   }

   public void setPreferredSize(int width, int height) {
      component.setSize(width, height);
   }

   public void setSize(int width, int height) {
      component.setSize(width, height);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, JComponentReal.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
      dc.nl();
      dc.appendVarWithSpace("ClassName", component.getClass().getName());
      dc.nl();
      slc.toSCD().dFind(component, dc);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, JComponentReal.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
      slc.getSwingCtx().toSD().dFind1(component, dc);
   }

   //#enddebug

}
