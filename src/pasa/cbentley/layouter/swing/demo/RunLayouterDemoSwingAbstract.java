/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.swing.JComponent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.interfaces.IPrefs;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src5.task.SaveCtxTask;
import pasa.cbentley.layouter.src4.engine.PozerFactory;
import pasa.cbentley.layouter.src4.engine.SizerFactory;
import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
import pasa.cbentley.layouter.swing.ctx.LayouterSwingCtx;
import pasa.cbentley.layouter.swing.demo.log.LogConfiguratorDemoLayouter;
import pasa.cbentley.layouter.swing.engine.JFrameLayouterDemo;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.swing.run.RunSwingAbstract;
import pasa.cbentley.swing.window.CBentleyFrame;

public abstract class RunLayouterDemoSwingAbstract extends RunSwingAbstract {

   protected final LayouterSwingCtx slc;

   protected final BOCtx            boc;

   protected PozerFactory           pozerFac;

   protected ByteObject             centerCenter;

   protected ByteObject             endToEnd;

   protected ByteObject             topToTop;

   protected ByteObject             pozerBottomToTop;

   protected ByteObject             pozerTopToBottom;

   protected ByteObject             pozerLeftToRight;

   protected ByteObject             pozerRighToRight;

   protected ByteObject             pozerEndtoEnd;

   protected ByteObject             pozerEndtoStart;

   protected ByteObject             pozerCenterToTop;

   protected ByteObject             pozerCenterToLeft;

   protected ByteObject             pozerCenterToRight;

   protected ByteObject             pozerCenterToBot;

   protected ByteObject             pozerYTop;

   protected ByteObject             startToStart;

   protected ByteObject             botToBot;

   protected Zer2DPozer             centerRightParent;

   protected Zer2DPozer             topEndParent;

   protected Zer2DPozer             midEndParent;

   protected Zer2DPozer             botEndParent;

   protected Zer2DPozer             topCenterParent;

   protected Zer2DPozer             midCenterParent;

   protected Zer2DPozer             botCenterParent;

   protected Zer2DPozer             topStartParent;

   protected Zer2DPozer             midStartParent;

   protected Zer2DPozer             botStartParent;

   private Runnable                 exitRun;

   protected SizerFactory           sizerFac;

   protected ByteObject             sizerRatio50;

   protected ByteObject             sizerRatio30;

   protected ByteObject             sizerRatio10;

   protected ByteObject             sizerRatio5;

   protected JPanelLayoutable       panel;

   private String                   fileName;

   public RunLayouterDemoSwingAbstract(IConfigU configU) {
      super(configU);
      boc = new BOCtx(uc);
      slc = new LayouterSwingCtx(boc, sc);
   }

   public void constructHelpers() {
      pozerFac = slc.getFactoryPozer();

      sizerFac = slc.getSizerFactory();

      sizerRatio50 = sizerFac.getSizerRatio100Parent(50);
      sizerRatio30 = sizerFac.getSizerRatio100Parent(30);
      sizerRatio10 = sizerFac.getSizerRatio100Parent(10);
      sizerRatio5 = sizerFac.getSizerRatio100Parent(5);

      centerCenter = pozerFac.getPozerCenterToCenter();

      endToEnd = pozerFac.getPozerBottomRight();

      topToTop = pozerFac.getPozerTopToTop();
      pozerBottomToTop = pozerFac.getPozerBotToTop();
      pozerTopToBottom = pozerFac.getPozerTopToBottom();
      pozerLeftToRight = pozerFac.getPozerTopToBottom();
      pozerRighToRight = pozerFac.getPozerTopToBottom();
      pozerEndtoEnd = pozerFac.getPozerEndToEnd();

      pozerEndtoStart = pozerFac.getPozerEndToStart();

      pozerCenterToTop = pozerFac.getPozerCenterToTopLeft();
      pozerCenterToLeft = pozerCenterToTop;
      pozerCenterToRight = pozerFac.getPozerCenterToBotRight();
      pozerCenterToBot = pozerCenterToRight;

      pozerYTop = pozerFac.getPozerTopLeft();
      startToStart = pozerFac.getPozerStartToStart();
      botToBot = pozerFac.getPozerBotToBot();

      centerRightParent = new Zer2DPozer(slc, endToEnd, pozerYTop);

      topEndParent = new Zer2DPozer(slc, endToEnd, topToTop);
      midEndParent = new Zer2DPozer(slc, endToEnd, centerCenter);
      botEndParent = new Zer2DPozer(slc, endToEnd, botToBot);
      topCenterParent = new Zer2DPozer(slc, centerCenter, topToTop);
      midCenterParent = new Zer2DPozer(slc, centerCenter, centerCenter);
      botCenterParent = new Zer2DPozer(slc, centerCenter, botToBot);
      topStartParent = new Zer2DPozer(slc, startToStart, topToTop);
      midStartParent = new Zer2DPozer(slc, startToStart, centerCenter);
      botStartParent = new Zer2DPozer(slc, startToStart, botToBot);

   }

   public Runnable getExitRun() {
      return exitRun;
   }

   /**
    * Add
    * @param exitRun
    */
   public void setExitRun(Runnable exitRun) {
      if (this.exitRun != null) {
         //multi run
         throw new RuntimeException("not implemented");
      }
      this.exitRun = exitRun;
   }

   public void cmdExit() {
      if (exitRun != null) {
         exitRun.run();
      }
   }

   /**
    * Add all the {@link JComponent} that you want
    * @param panel
    */
   public abstract void buildDemo(JPanelLayoutable panel);

   protected void addI18n(List<String> list) {

   }

   protected void initOutsideUIForPrefs(IPrefs prefs) {
      //create swing component that supports Drawer
      //reading from disk the core settings
      fileName = this.getClass().getSimpleName() + ".state";
      try {
         c5.loadCtxSettingsFromUserHome(fileName);
      } catch (IOException e) {
         e.printStackTrace();
      }

      setExitRun(new SaveCtxTask(c5, fileName));

   }

   protected CBentleyFrame initUIThreadInsideSwing() {
      JFrameLayouterDemo frame = new JFrameLayouterDemo(slc);
      panel = frame.getPanelLayouter();
      this.frame = frame;

      buildDemo(panel);

      frame.setTitle(this.getClass().getSimpleName());

      Image icon = sc.createImage("/logo_layouter_demo_64.png", "");

      frame.setIconImage(icon);
      //frame.showDefClosePackLocationNull();

      //#debug
      //uc.toDLog().pFlow("Start", frame, RunLayouterDemoSwingAbstract.class, "initUIThreadInsideSwing@line185");

      return frame;
   }

   public ILogConfigurator toStringGetLoggingConfig() {
      return new LogConfiguratorDemoLayouter();
   }
}
