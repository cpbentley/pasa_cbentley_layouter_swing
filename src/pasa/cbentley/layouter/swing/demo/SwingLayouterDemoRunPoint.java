/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.BaseDLogger;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.logging.ITechConfig;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.layouter.src4.engine.DebugBreakAdapter;
import pasa.cbentley.layouter.src4.engine.PozerFactory;
import pasa.cbentley.layouter.src4.engine.SizerFactory;
import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
import pasa.cbentley.layouter.src4.interfaces.IDebugBreaks;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.engine.DragPozerListener;
import pasa.cbentley.layouter.swing.widgets.BLComponent;
import pasa.cbentley.layouter.swing.widgets.BLFrame;
import pasa.cbentley.layouter.swing.widgets.BLPanel;
import pasa.cbentley.layouter.swing.widgets.LayoutableAdapter;

public class SwingLayouterDemoRunPoint {

   public static void main(String[] args) {

      UCtx uc = new UCtx();
      C5Ctx c5 = new C5Ctx(uc);
      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IDLogConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagPrint(ITechConfig.MASTER_FLAG_02_OPEN_ALL_PRINT, true);

      BOCtx boc = new BOCtx(uc);
      SwingLayouterCtx slc = new SwingLayouterCtx(uc, boc, c5);
      PozerFactory pozerFac = slc.getFactoryPozer();
      SizerFactory sizerFactory = slc.getSizerFactory();
      
      ByteObject sizer45PercentParent = sizerFactory.getSizerRatio100(ITechLayout.ETALON_4_PARENT, 45);
      ByteObject sizer40PercentParent = sizerFactory.getSizerRatio100(ITechLayout.ETALON_4_PARENT, 40);
      ByteObject sizer30PercentParent = sizerFactory.getSizerRatio100(ITechLayout.ETALON_4_PARENT, 30);
      ByteObject sizer10PercentParent = sizerFactory.getSizerRatio100(ITechLayout.ETALON_4_PARENT, 10);
      ByteObject sizer20PercentParent = sizerFactory.getSizerRatio100(ITechLayout.ETALON_4_PARENT, 10);
      ByteObject topToTop = pozerFac.getPozerTopToTop();
      ByteObject leftToLeft = pozerFac.getPozerTopToTop();
      ByteObject rightToRight = pozerFac.getPozerRightToRight();

      ByteObject pozerStart50 = pozerFac.getPozerAtTopStartForPixel(50);
      ByteObject pozerStart10 = pozerFac.getPozerAtTopStartForPixel(10);

      BLComponent red = new BLComponent(slc);
      red.setName("red");
      red.setBackground(Color.red);
      red.setSizer(sizer30PercentParent, sizer10PercentParent);
      red.setPozer(pozerStart50, pozerStart10);

      BLComponent green = new BLComponent(slc);
      green.setName("green");
      green.setBackground(Color.GREEN);
      green.setSizer(sizer30PercentParent, sizer40PercentParent);
      green.setPozer(rightToRight, topToTop);

      BLComponent orange = new BLComponent(slc);
      orange.setName("orange");
      orange.setBackground(Color.orange);
      LayoutableAdapter laOrange = orange.getLayoutableAdapter();
      laOrange.layPozStartToEndOf(red);
      laOrange.layPozEndToStartOf(green);
      laOrange.layPozTopToTopOfParent();
      laOrange.setSizerH(sizer10PercentParent);

      slc.setDebugBreaks(new DebugBreakAdapter() {

         public void checkForBreakPointSizeW(ILayoutable layoutable) {
         }

         public void checkForBreakPointSizeH(ILayoutable layoutable) {

         }

         public void checkForBreakPointSize(ILayoutable layoutable) {
            if (layoutable == laOrange) {
               isBreak = true;
            }
         }

         public void checkForBreakPointPosY(ILayoutable layoutable) {

         }

         public void checkForBreakPointPosX(ILayoutable layoutable) {
         }

         public void checkForBreakPointPos(ILayoutable layoutable) {
            if (layoutable == laOrange) {
               isBreak = true;
            }
         }
      });

      BLFrame frame = new BLFrame(slc);

      BLPanel panel = frame.getBLPanel();
      //order must not be important
      panel.addLayoutableOnTop(red);
      panel.addLayoutableOnTop(green);
      panel.addLayoutableOnTop(orange);

      //panel will have its size once packed
      frame.showDefClosePackLocationNull();

      //#debug
      slc.toDLog().pAlways("msg", frame, SwingLayouterDemoRunPoint.class, "main", IStringable.LVL_05_FINE, false);
   }

}
