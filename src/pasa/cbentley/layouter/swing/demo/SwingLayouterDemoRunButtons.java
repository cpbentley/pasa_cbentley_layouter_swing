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

public class SwingLayouterDemoRunButtons {

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
      
      ByteObject centerCenter = pozerFac.getPozerCenterToCenter();

      ByteObject endToEnd = pozerFac.getPozerBottomRight();

      ByteObject topToTop = pozerFac.getPozerTopToTop();
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
      ByteObject startToStart = pozerFac.getPozerStartToStart();
      ByteObject botToBot = pozerFac.getPozerBotToBot();
     
      Zer2DPozer centerRightParent = new Zer2DPozer(slc, endToEnd, pozerYTop);
      
      Zer2DPozer topEndParent = new Zer2DPozer(slc, endToEnd, topToTop);
      Zer2DPozer midEndParent = new Zer2DPozer(slc, endToEnd, centerCenter);
      Zer2DPozer botEndParent = new Zer2DPozer(slc, endToEnd, botToBot);
      Zer2DPozer topCenterParent = new Zer2DPozer(slc, centerCenter, topToTop);
      Zer2DPozer midCenterParent = new Zer2DPozer(slc, centerCenter, centerCenter);
      Zer2DPozer botCenterParent = new Zer2DPozer(slc, centerCenter, botToBot);
      Zer2DPozer topStartParent = new Zer2DPozer(slc, startToStart, topToTop);
      Zer2DPozer midStartParent = new Zer2DPozer(slc, startToStart, centerCenter);
      Zer2DPozer botStartParent = new Zer2DPozer(slc, startToStart, botToBot);

      BLFrame frame = new BLFrame(slc);

      BLPanel panel = frame.getBLPanel();

    
    
      JButton buttonTeachMe = new JButton("Teach Me!");
      buttonTeachMe.setName("teachMe");
      panel.addLayoutable(buttonTeachMe, topStartParent);
      
      JButton buttonKissMe = new JButton("Kiss Me!");
      buttonKissMe.setName("kissMe");
      LayoutableAdapter laKissMe = panel.addLayoutable(buttonKissMe, topCenterParent);
      
      JButton buttonGrowMe = new JButton("Grow Me!");
      buttonGrowMe.setName("growMe");
      //top right of button will be at the top center of parent
      panel.addLayoutable(buttonGrowMe, botStartParent);
      
      JButton buttonPushMe = new JButton("Push Me!");
      buttonPushMe.setName("pushMe");
      panel.addLayoutable(buttonPushMe, topEndParent);
      
      JButton buttonTeaseMe = new JButton("Tease Me!");
      buttonTeaseMe.setName("teaseMe");
      LayoutableAdapter laTeaseMe = panel.addLayoutable(buttonTeaseMe, midEndParent);
     
      JButton buttonTickleMe = new JButton("Tickle Me!");
      buttonTickleMe.setName("TickleMe");
      LayoutableAdapter laTickelMe = panel.addLayoutable(buttonTickleMe, midStartParent);
     
      JButton buttonLikeMe = new JButton("Like Me!");
      buttonLikeMe.setName("likeMe");
      LayoutableAdapter buttonLikeMeA = panel.addLayoutable(buttonLikeMe, midCenterParent);
     
      
      JButton buttonFeedMe = new JButton("Feed Me!");
      buttonFeedMe.setName("feedMe");
      LayoutableAdapter laFeedMe = panel.addLayoutable(buttonFeedMe, botCenterParent);
      
      JButton buttonLoveMe = new JButton("Love Me!");
      buttonLoveMe.setName("loveMe");
      panel.addLayoutable(buttonLoveMe, botEndParent);
      
      
      JButton buttonMove = new JButton("Move Me1!");
      buttonMove.setName("moveMe1");
      //long way to do it
      LayoutableAdapter laButtonMove = new LayoutableAdapter(slc, buttonMove);
      laButtonMove.layPozTopToBotOf(buttonLikeMeA);
      laButtonMove.layPozEndToStartOf(buttonLikeMeA);
      laButtonMove.laySizePreferred();
      panel.addLayoutable(laButtonMove);
      
      JButton buttonMove2 = new JButton("Move Me2!");
      buttonMove2.setName("moveMe2");
      //long way to do it
      LayoutableAdapter laButtonMove2 = new LayoutableAdapter(slc, buttonMove2);
      laButtonMove2.layPozBotToTopOf(buttonLikeMeA);
      laButtonMove2.layPozEndToStartOf(buttonLikeMeA);
      laButtonMove2.laySizePreferred();
      panel.addLayoutable(laButtonMove2);
      
      JButton buttonMove3 = new JButton("Move Me3!");
      buttonMove3.setName("moveMe3");
      //long way to do it
      LayoutableAdapter laButtonMove3 = new LayoutableAdapter(slc, buttonMove3);
      laButtonMove3.layPozBotToTopOf(buttonLikeMeA);
      laButtonMove3.layPozStartToEndOf(buttonLikeMeA);
      laButtonMove3.laySizePreferred();
      panel.addLayoutable(laButtonMove3);
      
      JButton buttonMove4 = new JButton("Move Me4!");
      buttonMove4.setName("moveMe4");
      //long way to do it
      LayoutableAdapter laButtonMove4 = new LayoutableAdapter(slc, buttonMove4);
      laButtonMove4.layPozTopToBotOf(buttonLikeMeA);
      laButtonMove4.layPozStartToEndOf(buttonLikeMeA);
      laButtonMove4.laySizePreferred();
      panel.addLayoutable(laButtonMove4);
      
      JButton buttonTease2 = new JButton("Tease Me2!");
      buttonTease2.setName("moveMe3");
      //long way to do it
      LayoutableAdapter laButtonTease2 = new LayoutableAdapter(slc, buttonTease2);
      laButtonTease2.layPozTopToMidOf(laTeaseMe);
      laButtonTease2.layPozEndToStartOf(laTeaseMe);
      laButtonTease2.laySizePreferred();
      panel.addLayoutable(laButtonTease2);
      
      JButton buttonTease3 = new JButton("Tease Me2!");
      buttonTease3.setName("moveMe3");
      //long way to do it
      LayoutableAdapter laButtonTease3 = new LayoutableAdapter(slc, buttonTease3);
      laButtonTease3.layPozBotToMidOf(laTeaseMe);
      laButtonTease3.layPozEndToStartOf(laTeaseMe);
      laButtonTease3.laySizePreferred();
      panel.addLayoutable(laButtonTease3);
      
      
      JButton buttonKissMe2 = new JButton("Kiss Me2!");
      buttonKissMe2.setName("KissMe3");
      //long way to do it
      LayoutableAdapter laButtonKissMe2 = new LayoutableAdapter(slc, buttonKissMe2);
      laButtonKissMe2.layPozTopToBotOf(laKissMe);
      laButtonKissMe2.layPozEndToCenterOf(laKissMe);
      laButtonKissMe2.laySizePreferred();
      panel.addLayoutable(laButtonKissMe2);
      
      JButton buttonKissMe3 = new JButton("Kiss Me3!");
      buttonKissMe3.setName("KissMe3");
      //long way to do it
      LayoutableAdapter laButtonKissMe3 = new LayoutableAdapter(slc, buttonKissMe3);
      laButtonKissMe3.layPozTopToBotOf(laKissMe);
      laButtonKissMe3.layPozStartToCenterOf(laKissMe);
      laButtonKissMe3.laySizePreferred();
      panel.addLayoutable(laButtonKissMe3);
      
      JButton buttonFeedMe2 = new JButton("Feed Me2!");
      buttonKissMe2.setName("FeedMe3");
      //long way to do it
      LayoutableAdapter laButtonFeedMe2 = new LayoutableAdapter(slc, buttonFeedMe2);
      laButtonFeedMe2.layPozBotToTopOf(laFeedMe);
      laButtonFeedMe2.layPozEndToCenterOf(laFeedMe);
      laButtonFeedMe2.laySizePreferred();
      panel.addLayoutable(laButtonFeedMe2);
      
      JButton buttonFeedMe3 = new JButton("Feed Me3!");
      buttonFeedMe3.setName("FeedMe3");
      //long way to do it
      LayoutableAdapter laButtonFeedMe3 = new LayoutableAdapter(slc, buttonFeedMe3);
      laButtonFeedMe3.layPozBotToTopOf(laFeedMe);
      laButtonFeedMe3.layPozStartToCenterOf(laFeedMe);
      laButtonFeedMe3.laySizePreferred();
      panel.addLayoutable(laButtonFeedMe3);
      
      JButton buttonTickleMe2 = new JButton("Tickle Me2!");
      buttonKissMe2.setName("TickleMe3");
      //long way to do it
      LayoutableAdapter laButtonTickleMe2 = new LayoutableAdapter(slc, buttonTickleMe2);
      laButtonTickleMe2.layPozBotToMidOf(laTickelMe);
      laButtonTickleMe2.layPozStartToEndOf(laTickelMe);
      laButtonTickleMe2.laySizePreferred();
      panel.addLayoutable(laButtonTickleMe2);
      
      JButton buttonTickleMe3 = new JButton("Tickle Me3!");
      buttonTickleMe3.setName("TickleMe3");
      //long way to do it
      LayoutableAdapter laButtonTickleMe3 = new LayoutableAdapter(slc, buttonTickleMe3);
      laButtonTickleMe3.layPozTopToMidOf(laTickelMe);
      laButtonTickleMe3.layPozStartToEndOf(laTickelMe);
      laButtonTickleMe3.laySizePreferred();
      panel.addLayoutable(laButtonTickleMe3);
      
      slc.setDebugBreaks(new DebugBreakAdapter() {
         
         public void checkForBreakPointSizeW(ILayoutable layoutable) {
            if(layoutable == buttonLikeMeA) {
               isBreak = true;
            }
         }
         
         public void checkForBreakPointSizeH(ILayoutable layoutable) {
         }
         
         public void checkForBreakPointSize(ILayoutable layoutable) {
       
         }
         
         public void checkForBreakPointPosY(ILayoutable layoutable) {
            
         }
         
         public void checkForBreakPointPosX(ILayoutable layoutable) {
            
         }
         
         public void checkForBreakPointPos(ILayoutable layoutable) {
            if(layoutable == laButtonMove4) {
               isBreak = true;
            }
         }
      });
  

      frame.showDefClosePackLocationNull();

   }

}
