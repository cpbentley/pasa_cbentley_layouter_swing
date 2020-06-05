/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.engine.PozerFactory;
import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.engine.ConfiguratorForSwing;
import pasa.cbentley.layouter.swing.engine.DragPozerListener;
import pasa.cbentley.layouter.swing.engine.JComponentLayoutable;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.KeyListenerF4DebugStringable;
import pasa.cbentley.layouter.swing.engine.LayEngineSwing;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;

public class RunLayouterDemoSwingMoveButton extends RunLayouterDemoSwingAbstract {

   public RunLayouterDemoSwingMoveButton(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void buildDemo(JPanelLayoutable panel) {
      buildDemo(panel.getConfigurator());
   }

   public void buildDemo(ConfiguratorForSwing configurator) {

      ILayoutable parent = configurator.getPanel();

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

      JComponentLayoutable green = new JComponentLayoutable(slc);
      green.setName("green");
      green.setBackground(Color.GREEN);
      green.setSizer(sizerW, sizerH);
      green.setPozer(pozerX, pozerY);

      JComponentLayoutable yellow = new JComponentLayoutable(slc);
      yellow.setName("yellow");
      yellow.setBackground(Color.YELLOW);
      yellow.setParentLayout(green);
      yellow.setSizer(sizer40, sizer40);
      yellow.setPozer(pozerX, pozerY);

      JComponentLayoutable blue = new JComponentLayoutable(slc);
      blue.setName("blue");
      blue.setBackground(Color.BLUE);
      blue.setParentLayout(green);
      blue.setSizer(sizer60, sizer40);
      blue.setPozer(pozerCenterToRight, pozerCenterToTop);

      JComponentLayoutable red = new JComponentLayoutable(slc);
      red.setName("red");
      red.setBackground(Color.red);
      red.setParentLayout(green);
      red.setSizer(sizerRatio40, sizerRatio16_9);
      red.setPozer(pozerCenterToLeft, pozerCenterToBot);

      JComponentLayoutable pink = new JComponentLayoutable(slc);
      pink.setName("pink");
      pink.setBackground(Color.pink);
      pink.setParentLayout(green);
      pink.setSizer(sizer60, sizer40);
      pink.setPozer(pozerEndtoEnd, pozerTopToBottom);

      JComponentLayoutable orange = new JComponentLayoutable(slc);
      orange.setName("orange");
      orange.setBackground(Color.orange);
      orange.setParentLayout(green);
      orange.setSizer(sizerRatio50, sizerRatio50);
      orange.setPozer(pozerEndtoStart, pozerTopToTop);

      JComponentLayoutable trans = new JComponentLayoutable(slc);
      trans.setName("trans");
      trans.setBackground(new Color(40, 210, 100, 128));
      LayEngineSwing laTrans = trans.getLayoutableAdapter();
      //size is defined by anchor points
      laTrans.getLay().layPoz_StartToEnd_Of(red);
      laTrans.getLay().layPoz_EndToStart_Of(pink);
      laTrans.getLay().layPoz_TopToBot_Of(blue);
      laTrans.getLay().layPoz_BotToMid_Of(green);

      //order must not be important
      configurator.addLayoutableOnTop(green);
      configurator.addLayoutableOnTop(yellow);
      configurator.addLayoutableOnTop(blue);
      configurator.addLayoutableOnTop(red);
      configurator.addLayoutableOnTop(pink);
      configurator.addLayoutableOnTop(orange);
      configurator.addLayoutableOnTop(trans);

      JButton buttonMove = new JButton("Move Me!");
      buttonMove.setName("buttonMoveMe");
      LayoutableAdapterForJComponent buttonMoveLay = configurator.createLayoutableFromSwingPref(buttonMove);
      buttonMoveLay.getLay().layPoz_BotToBot_OfParent();
      buttonMoveLay.getLay().layPoz_MidXToMid_OfParent();

      DragPozerListener listener = new DragPozerListener(slc, buttonMoveLay, panel);

      KeyListenerF4DebugStringable keyLis = new KeyListenerF4DebugStringable(slc, panel);
      
      buttonMove.addKeyListener(keyLis);
      buttonMove.addMouseListener(listener);
      buttonMove.addMouseMotionListener(listener);
      buttonMove.addMouseWheelListener(listener);

      JComponentLayoutable moveMeSatellite = new JComponentLayoutable(slc);
      moveMeSatellite.setName("pushMeSatellite");
      moveMeSatellite.setBackground(new Color(40, 210, 100, 198));
      moveMeSatellite.getLay().layPoz_EndToStart_Of(buttonMoveLay);
      moveMeSatellite.getLay().layPoz_TopToTop_Of(buttonMoveLay);
      moveMeSatellite.getLay().layPoz_BotToBot_Of(buttonMoveLay);
      //we need width or use preferred size
      moveMeSatellite.getLay().setSizerW(sizer40);

      JButton buttonMoveSat = new JButton("I am a Satellite");
      buttonMove.setName("buttonMoveMeSatellite");
      LayoutableAdapterForJComponent buttonMoveSatLay = configurator.createLayoutableFromSwingPref(buttonMoveSat);
      buttonMoveSatLay.getLay().layPoz_Corner_3To1_Of(buttonMoveLay);
      buttonMoveSatLay.getLay().setSizerW(sizerRatio50);
      buttonMoveSatLay.getLay().setSizerH(sizer60);

      JTextPane consoleText = new JTextPane();
      JScrollPane jsp = new JScrollPane(consoleText);
      jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
      jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      jsp.setName("consoleScrollPane");

      configurator.addLayoutableCornersToCorners(jsp, 1, 4, pink, 3, 3, null);


      JButton buttonGrow = new JButton("Grow Me!");
      buttonGrow.setName("growMe");
      //top right of button will be at the top center of parent
      ILayoutable addLayoutable = configurator.addLayoutable(buttonGrow, pozerEndToCenterTopToTopParent);

      JButton buttonKissMe = new JButton("Wheel Me!");
      buttonKissMe.setName("wheelMe");

      JButton buttonPushMe = new JButton("Debug");
      buttonPushMe.setName("buttonDebug");
      buttonPushMe.addKeyListener(keyLis);

      //will take its preferred size
      configurator.addLayoutable(buttonPushMe, top2TopRight2RightParent);

      //#mdebug
      //this is one of those rare case when an Anonymous class makes sense.
      //we want to manipulate the state at will for setting the breakpoints
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
         }

         public void layoutWillComputeSizeH(ILayoutable layoutable) {
         }

         public void layoutWillComputeSizes(ILayoutable layoutable) {
         }

         public void layoutWillComputePositionY(ILayoutable layoutable) {
         }

         public void layoutWillComputePositionX(ILayoutable layoutable) {
         }

         public void layoutWillComputePositions(ILayoutable layoutable) {
            if (layoutable == null) {
               isBreak = true;
            }
         }
      });
      //#enddebug
   }

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingMoveButton runner = new RunLayouterDemoSwingMoveButton(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
