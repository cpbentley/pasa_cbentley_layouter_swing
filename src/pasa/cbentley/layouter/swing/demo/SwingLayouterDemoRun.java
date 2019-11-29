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
import pasa.cbentley.core.src4.logging.IConfig;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.logging.ITechConfig;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.layouter.src4.engine.DebugBreakAdapter;
import pasa.cbentley.layouter.src4.engine.PozerFactory;
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

public class SwingLayouterDemoRun {

   public static void main(String[] args) {

      UCtx uc = new UCtx();
      C5Ctx c5 = new C5Ctx(uc);
      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      config.setFlagPrint(ITechConfig.MASTER_FLAG_02_OPEN_ALL_PRINT, true);

      BOCtx boc = new BOCtx(uc);
      SwingLayouterCtx slc = new SwingLayouterCtx(uc, boc, c5);

      ByteObject sizerW = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 30);
      ByteObject sizerH = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 40);

      ByteObject sizerRatio40 = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 40);

      //TODO etalon is the opposite
      ByteObject sizerRatio16_9 = slc.getSizerFactory().getSizerRatioFraction(16, 9);

      PozerFactory pozerFac = slc.getFactoryPozer();
      ByteObject pozerX = pozerFac.getPozerCenterToCenter();
      ByteObject pozerY = pozerFac.getPozerCenterToCenter();

      ByteObject pozerCenterCenter = pozerFac.getPozerCenterToCenter();
      ByteObject pozerEndCenter = pozerFac.getPozerEndToCenter();

      ByteObject pozerXRightToRight = pozerFac.getPozerBottomRight();

      ByteObject pozerTopToTop = pozerFac.getPozerTopToTop();
      ByteObject pozerBottomToTop = pozerFac.getPozerBotToTop();
      ByteObject pozerTopToBottom = pozerFac.getPozerTopToBottom();
      ByteObject pozerLeftToRight = pozerFac.getPozerTopToBottom();
      ByteObject pozerRighToRight = pozerFac.getPozerTopToBottom();
      ByteObject pozerEndtoEnd = pozerFac.getPozerEndToEnd();

      ByteObject pozerEndtoStart = pozerFac.getPozerEndToStart();

      ByteObject pozerCenterToTop = pozerFac.getPozerCenterToTopLeft();
      ByteObject pozerCenterToLeft = pozerCenterToTop;
      ByteObject pozerCenterToRight = pozerFac.getPozerCenterToBotRight();
      ByteObject pozerCenterToBot = pozerCenterToRight;

      ByteObject pozerYTop = pozerFac.getPozerTopLeft();

      Zer2DPozer top2TopRight2RightParent = new Zer2DPozer(slc, pozerXRightToRight, pozerTopToTop);

      Zer2DPozer centerRightParent = new Zer2DPozer(slc, pozerXRightToRight, pozerYTop);

      Zer2DPozer pozerEndToCenterTopToTopParent = new Zer2DPozer(slc, pozerEndCenter, pozerTopToTop);

      ByteObject sizer40 = slc.getSizerFactory().getSizerPix(40);
      ByteObject sizer60 = slc.getSizerFactory().getSizerPix(60);

      ByteObject sizerRatio50 = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 50);

      BLComponent green = new BLComponent(slc);
      green.setName("green");
      green.setBackground(Color.GREEN);
      green.setSizer(sizerW, sizerH);
      green.setPozer(pozerX, pozerY);

      BLComponent yellow = new BLComponent(slc);
      yellow.setName("yellow");
      yellow.setBackground(Color.YELLOW);
      yellow.setParentLayout(green);
      yellow.setSizer(sizer40, sizer40);
      yellow.setPozer(pozerX, pozerY);

      BLComponent blue = new BLComponent(slc);
      blue.setName("blue");
      blue.setBackground(Color.BLUE);
      blue.setParentLayout(green);
      blue.setSizer(sizer60, sizer40);
      blue.setPozer(pozerCenterToRight, pozerCenterToTop);

      BLComponent red = new BLComponent(slc);
      red.setName("red");
      red.setBackground(Color.red);
      red.setParentLayout(green);
      red.setSizer(sizerRatio40, sizerRatio16_9);
      red.setPozer(pozerCenterToLeft, pozerCenterToBot);

      BLComponent pink = new BLComponent(slc);
      pink.setName("pink");
      pink.setBackground(Color.pink);
      pink.setParentLayout(green);
      pink.setSizer(sizer60, sizer40);
      pink.setPozer(pozerEndtoEnd, pozerTopToBottom);

      BLComponent orange = new BLComponent(slc);
      orange.setName("orange");
      orange.setBackground(Color.orange);
      orange.setParentLayout(green);
      orange.setSizer(sizerRatio50, sizerRatio50);
      orange.setPozer(pozerEndtoStart, pozerTopToTop);

      BLComponent trans = new BLComponent(slc);
      trans.setName("trans");
      trans.setBackground(new Color(40, 210, 100, 128));
      LayoutableAdapter laTrans = trans.getLayoutableAdapter();
      //size is defined by anchor points
      laTrans.layPozStartToEndOf(red);
      laTrans.layPozEndToStartOf(pink);
      laTrans.layPozTopToBotOf(blue);
      laTrans.layPozBotToMidOf(green);

      //#debug
      slc.toDLog().pFlow("Trans", laTrans, SwingLayouterDemoRun.class, "main", IStringable.LVL_05_FINE, false);

      BLFrame frame = new BLFrame(slc);

      BLPanel panel = frame.getBLPanel();

      //order must not be important
      panel.addLayoutableOnTop(green);
      panel.addLayoutableOnTop(yellow);
      panel.addLayoutableOnTop(blue);
      panel.addLayoutableOnTop(red);
      panel.addLayoutableOnTop(pink);
      panel.addLayoutableOnTop(orange);
      panel.addLayoutableOnTop(trans);

      JButton buttonMove = new JButton("Move Me!");
      buttonMove.setName("buttonMoveMe");
      LayoutableAdapter laButtonMove = new LayoutableAdapter(slc, buttonMove);
      laButtonMove.layPozBotToBotOfParent();
      laButtonMove.layPozMidXToMidOfParent();
      laButtonMove.laySizePreferred();

      DragPozerListener listener = new DragPozerListener(slc, laButtonMove, panel);
      buttonMove.addKeyListener(frame.getKeyListenerRoot());
      buttonMove.addMouseListener(listener);
      buttonMove.addMouseMotionListener(listener);
      buttonMove.addMouseWheelListener(listener);

      panel.addLayoutable(laButtonMove);

      BLComponent moveMeSatellite = new BLComponent(slc);
      moveMeSatellite.setName("pushMeSatellite");
      moveMeSatellite.setBackground(new Color(40, 210, 100, 198));
      LayoutableAdapter laMoveMeSatellite = moveMeSatellite.getLayoutableAdapter();
      //size is defined by anchor points
      laMoveMeSatellite.layPozEndToStartOf(laButtonMove);
      laMoveMeSatellite.layPozTopToTopOf(laButtonMove);
      laMoveMeSatellite.layPozBotToBotOf(laButtonMove);
      //we need width or use preferred size
      laMoveMeSatellite.setSizerW(sizer40);

      panel.addLayoutable(laMoveMeSatellite);

      JTextPane consoleText = new JTextPane();
      JScrollPane jsp = new JScrollPane(consoleText);
      jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
      jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      jsp.setName("consoleScrollPane");

      LayoutableAdapter laJS = new LayoutableAdapter(slc, jsp);
      laJS.layPozCorner1ToCorner4Of(pink);
      laJS.layPozCorner3ToCorner3OfParent();

      panel.addLayoutable(laJS);

      JButton buttonGrow = new JButton("Grow Me!");
      buttonGrow.setName("growMe");
      //top right of button will be at the top center of parent
      panel.addLayoutable(buttonGrow, pozerEndToCenterTopToTopParent);

      JButton buttonKissMe = new JButton("Wheel Me!");
      buttonKissMe.setName("wheelMe");

      JButton buttonPushMe = new JButton("Push Me!");
      buttonPushMe.setName("buttonPushMe");
      buttonPushMe.addKeyListener(frame.getKeyListenerRoot());
      //will take its preferred size
      panel.addLayoutable(buttonPushMe, top2TopRight2RightParent);

      slc.setDebugBreaks(new DebugBreakAdapter() {

         public void checkForBreakPointSizeW(ILayoutable layoutable) {
            if (layoutable == laTrans) {
               isBreak = true;
            }
         }

         public void checkForBreakPointSizeH(ILayoutable layoutable) {

         }

         public void checkForBreakPointSize(ILayoutable layoutable) {
            if (layoutable == laJS) {
               isBreak = true;
            }
            if (layoutable == laMoveMeSatellite) {
               isBreak = true;
            }
         }

         public void checkForBreakPointPosY(ILayoutable layoutable) {

         }

         public void checkForBreakPointPosX(ILayoutable layoutable) {

         }

         public void checkForBreakPointPos(ILayoutable layoutable) {
            if (layoutable == laJS) {
               isBreak = true;
            }
            if (layoutable == laTrans) {
               isBreak = true;
            }
            if (layoutable == laMoveMeSatellite) {
               isBreak = true;
            }

         }
      });

      frame.showDefClosePackLocationNull();

      appendToPane(consoleText, "Start!", Color.red);
   }

   public static void appendToPane(JTextPane tp, String msg, Color c) {
      StyleContext sc = StyleContext.getDefaultStyleContext();
      AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

      aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
      aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

      int len = tp.getDocument().getLength();
      tp.setCaretPosition(len);
      tp.setCharacterAttributes(aset, false);
      tp.replaceSelection(msg);
   }
}
