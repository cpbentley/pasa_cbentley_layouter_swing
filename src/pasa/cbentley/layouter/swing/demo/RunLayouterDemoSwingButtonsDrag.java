/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import javax.swing.JButton;

import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.layouter.src4.engine.Area2DConfigurator;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;

/**
 * 4 buttons.
 * one panel anchored at their location
 * @author Charles Bentley
 *
 */
public class RunLayouterDemoSwingButtonsDrag extends RunLayouterDemoSwingAbstract {

   public RunLayouterDemoSwingButtonsDrag(IConfigU configU) {
      super(configU);

      constructHelpers();

   }


   public void buildDemo(JPanelLayoutable panel) {
      JButton buttonTeachMe = new JButton("Teach Me!");
      buttonTeachMe.setName("teachMe");
      panel.addLayoutable(buttonTeachMe, topStartParent);

      JButton buttonKissMe = new JButton("Kiss Me!");
      buttonKissMe.setName("kissMe");
      LayoutableAdapterForJComponent laKissMe = panel.addLayoutable(buttonKissMe, topCenterParent);

      JButton buttonGrowMe = new JButton("Grow Me!");
      buttonGrowMe.setName("growMe");
      //top right of button will be at the top center of parent
      panel.addLayoutable(buttonGrowMe, botStartParent);

      JButton buttonPushMe = new JButton("Push Me!");
      buttonPushMe.setName("pushMe");
      panel.addLayoutable(buttonPushMe, topEndParent);

      JButton buttonTeaseMe = new JButton("Tease Me!");
      buttonTeaseMe.setName("teaseMe");
      LayoutableAdapterForJComponent laTeaseMe = panel.addLayoutable(buttonTeaseMe, midEndParent);

      JButton buttonTickleMe = new JButton("Tickle Me!");
      buttonTickleMe.setName("TickleMe");
      LayoutableAdapterForJComponent laTickelMe = panel.addLayoutable(buttonTickleMe, midStartParent);

      JButton buttonLikeMe = new JButton("Like Me!");
      buttonLikeMe.setName("likeMe");
      LayoutableAdapterForJComponent buttonLikeMeA = panel.addLayoutable(buttonLikeMe, midCenterParent);

      JButton buttonFeedMe = new JButton("Feed Me!");
      buttonFeedMe.setName("feedMe");
      LayoutableAdapterForJComponent laFeedMe = panel.addLayoutable(buttonFeedMe, botCenterParent);

      JButton buttonLoveMe = new JButton("Love Me!");
      buttonLoveMe.setName("loveMe");
      panel.addLayoutable(buttonLoveMe, botEndParent);

      JButton buttonMove = new JButton("Move Me1!");
      buttonMove.setName("moveMe1");
      //long way to do it
      LayoutableAdapterForJComponent laButtonMove = new LayoutableAdapterForJComponent(slc, buttonMove);
      laButtonMove.getLay().layPoz_TopToBot_Of(buttonLikeMeA);
      laButtonMove.getLay().layPoz_EndToStart_Of(buttonLikeMeA);
      laButtonMove.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonMove);

      JButton buttonMove2 = new JButton("Move Me2!");
      buttonMove2.setName("moveMe2");
      //long way to do it
      LayoutableAdapterForJComponent buttonMove2LA = slc.createFor(buttonMove2);
      Area2DConfigurator lay = buttonMove2LA.getLay();
      lay.layPoz_BotToTop_Of(buttonLikeMeA);
      lay.layPoz_EndToStart_Of(buttonLikeMeA);
      lay.laySiz_Preferred();
      panel.addLayoutable(buttonMove2LA);

      JButton buttonMove3 = new JButton("Move Me3!");
      buttonMove3.setName("moveMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonMove3 = new LayoutableAdapterForJComponent(slc, buttonMove3);
      Area2DConfigurator configurator = laButtonMove3.getLay();
      configurator.layPoz_BotToTop_Of(buttonLikeMeA);
      configurator.layPoz_StartToEnd_Of(buttonLikeMeA);
      configurator.laySiz_Preferred();
      panel.addLayoutable(laButtonMove3);

      JButton buttonMove4 = new JButton("Move Me4!");
      buttonMove4.setName("moveMe4");
      //long way to do it
      LayoutableAdapterForJComponent laButtonMove4 = new LayoutableAdapterForJComponent(slc, buttonMove4);
      laButtonMove4.getLay().layPoz_TopToBot_Of(buttonLikeMeA);
      laButtonMove4.getLay().layPoz_StartToEnd_Of(buttonLikeMeA);
      laButtonMove4.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonMove4);

      JButton buttonTease2 = new JButton("Tease Me2!");
      buttonTease2.setName("moveMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonTease2 = new LayoutableAdapterForJComponent(slc, buttonTease2);
      laButtonTease2.getLay().layPoz_TopToMid_Of(laTeaseMe);
      laButtonTease2.getLay().layPoz_EndToStart_Of(laTeaseMe);
      laButtonTease2.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonTease2);

      JButton buttonTease3 = new JButton("Tease Me2!");
      buttonTease3.setName("moveMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonTease3 = new LayoutableAdapterForJComponent(slc, buttonTease3);
      laButtonTease3.getLay().layPoz_BotToMid_Of(laTeaseMe);
      laButtonTease3.getLay().layPoz_EndToStart_Of(laTeaseMe);
      laButtonTease3.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonTease3);

      JButton buttonKissMe2 = new JButton("Kiss Me2!");
      buttonKissMe2.setName("KissMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonKissMe2 = new LayoutableAdapterForJComponent(slc, buttonKissMe2);
      laButtonKissMe2.getLay().layPoz_TopToBot_Of(laKissMe);
      laButtonKissMe2.getLay().layPoz_EndToCenter_Of(laKissMe);
      laButtonKissMe2.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonKissMe2);

      JButton buttonKissMe3 = new JButton("Kiss Me3!");
      buttonKissMe3.setName("KissMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonKissMe3 = new LayoutableAdapterForJComponent(slc, buttonKissMe3);
      laButtonKissMe3.getLay().layPoz_TopToBot_Of(laKissMe);
      laButtonKissMe3.getLay().layPoz_StartToCenter_Of(laKissMe);
      laButtonKissMe3.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonKissMe3);

      JButton buttonFeedMe2 = new JButton("Feed Me2!");
      buttonKissMe2.setName("FeedMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonFeedMe2 = new LayoutableAdapterForJComponent(slc, buttonFeedMe2);
      laButtonFeedMe2.getLay().layPoz_BotToTop_Of(laFeedMe);
      laButtonFeedMe2.getLay().layPoz_EndToCenter_Of(laFeedMe);
      laButtonFeedMe2.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonFeedMe2);

      JButton buttonFeedMe3 = new JButton("Feed Me3!");
      buttonFeedMe3.setName("FeedMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonFeedMe3 = new LayoutableAdapterForJComponent(slc, buttonFeedMe3);
      laButtonFeedMe3.getLay().layPoz_BotToTop_Of(laFeedMe);
      laButtonFeedMe3.getLay().layPoz_StartToCenter_Of(laFeedMe);
      laButtonFeedMe3.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonFeedMe3);

      JButton buttonTickleMe2 = new JButton("Tickle Me2!");
      buttonKissMe2.setName("TickleMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonTickleMe2 = new LayoutableAdapterForJComponent(slc, buttonTickleMe2);
      laButtonTickleMe2.getLay().layPoz_BotToMid_Of(laTickelMe);
      laButtonTickleMe2.getLay().layPoz_StartToEnd_Of(laTickelMe);
      laButtonTickleMe2.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonTickleMe2);

      JButton buttonTickleMe3 = new JButton("Tickle Me3!");
      buttonTickleMe3.setName("TickleMe3");
      //long way to do it
      LayoutableAdapterForJComponent laButtonTickleMe3 = new LayoutableAdapterForJComponent(slc, buttonTickleMe3);
      laButtonTickleMe3.getLay().layPoz_TopToMid_Of(laTickelMe);
      laButtonTickleMe3.getLay().layPoz_StartToEnd_Of(laTickelMe);
      laButtonTickleMe3.getLay().laySiz_Preferred();
      panel.addLayoutable(laButtonTickleMe3);

      //add listener for computations
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
            if (layoutable == buttonLikeMeA) {
               isBreak = true;
            }
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
            if (layoutable == laButtonMove4) {
               isBreak = true;
            }
         }
      });

   }

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);
      
      RunLayouterDemoSwingButtonsDrag runner = new RunLayouterDemoSwingButtonsDrag(configU);
      
      //start inside the swing runner framework
      runner.run();
    
   }



}
