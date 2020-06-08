/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.structs.IntToObjects;
import pasa.cbentley.layouter.src4.engine.Area2DConfigurator;
import pasa.cbentley.layouter.src4.engine.LayoutableGhost;
import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
import pasa.cbentley.layouter.src4.interfaces.ILayoutWillListener;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechPozer;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.interfaces.ILayoutableSwing;

public class ConfiguratorForSwing implements IStringable {

   protected IntToObjects           extras;

   protected JPanelLayoutable       panel;

   protected final SwingLayouterCtx slc;

   public ConfiguratorForSwing(SwingLayouterCtx slc, JPanelLayoutable panel) {
      this.slc = slc;
      this.panel = panel;
      extras = new IntToObjects(slc.getUCtx());

   }

   /**
    * 
    * @param c
    * @param pozerCorner1
    * @return
    */
   public ILayoutable addLayoutable(JComponent c, Zer2DPozer pozerCorner1) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();
      configurator.setPozerCorner1(pozerCorner1);
      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);
      return adapter;
   }

   public ILayoutableSwing addLayoutableCornersToCorners(JComponent c, int cornerAPozee, int cornerA, ILayoutableSwing layA, int cornerBPozee, int cornerB, ILayoutableSwing layB) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();
      configurator.layPoz_Corner_Of(cornerAPozee, cornerA, layA);
      configurator.layPoz_Corner_Of(cornerBPozee, cornerB, layB);
      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);
      return adapter;
   }

   /**
    * Lay Component "between" parameters.
    * 
    * at the end of start until the start of end.
    * 
    * @param c
    * @param start
    * @param end
    */
   public void addLayoutableEndStart(JComponent c, ILayoutable start, ILayoutable end) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();
      configurator.layPoz_StartToEnd_Of(start);
      configurator.layPoz_EndToStart_Of(end);

      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);
   }

   public ILayoutable addInsideStartEndTopBot(ILayoutableSwing c, ILayoutable all) {
      return addInsideStartEndTopBot(c, all, all, all, all);
   }

   public ILayoutable addInsideStartEndTopBot_Margin(ILayoutableSwing c, ILayoutable all, ByteObject margin) {
      return addInsideStartEndTopBot_Margin(c, all, all, all, all, margin);
   }

   public ILayoutable addInsideStartEndTopBot_Padding(ILayoutableSwing c, ILayoutable all, ByteObject margin) {
      return addInsideStartEndTopBot_Padding(c, all, all, all, all, margin);
   }

   public ILayoutable addInsideStartEndTopBot(ILayoutableSwing c, ILayoutable startToStart, ILayoutable endToEnd, ILayoutable topToTop, ILayoutable botToBot) {
      Area2DConfigurator configurator = new Area2DConfigurator(slc, c.getArea());

      configurator.layPoz_StartToStart_Of(startToStart);
      configurator.layPoz_EndToEnd_Of(endToEnd);

      configurator.layPoz_BotToBot_Of(botToBot);
      configurator.layPoz_TopToTop_Of(topToTop);

      panel.addLayoutableNoCheck(c);

      return c;
   }

   public ILayoutable addInsideStartEndTopBot_Padding(ILayoutableSwing c, ILayoutable startToStart, ILayoutable endToEnd, ILayoutable topToTop, ILayoutable botToBot, ByteObject margin) {
      Area2DConfigurator configurator = new Area2DConfigurator(slc, c.getArea());

      configurator.layPoz_StartToStart_Of_Padding(startToStart, margin);
      configurator.layPoz_EndToEnd_Of_Padding(endToEnd, margin);

      configurator.layPoz_BotToBot_Of_Padding(botToBot, margin);
      configurator.layPoz_TopToTop_Of_Padding(topToTop, margin);

      panel.addLayoutableNoCheck(c);

      return c;
   }

   public ILayoutable addInsideStartEndTopBot_Margin(ILayoutableSwing c, ILayoutable startToStart, ILayoutable endToEnd, ILayoutable topToTop, ILayoutable botToBot, ByteObject margin) {
      Area2DConfigurator configurator = new Area2DConfigurator(slc, c.getArea());

      configurator.layPoz_StartToStart_Of_Margin(startToStart, margin);
      configurator.layPoz_EndToEnd_Of_Margin(endToEnd, margin);

      configurator.layPoz_BotToBot_Of_Margin(botToBot, margin);
      configurator.layPoz_TopToTop_Of_Margin(topToTop, margin);

      panel.addLayoutableNoCheck(c);

      return c;
   }

   public ILayoutable addLayoutableInsideStartEndTopBot(JComponent c, ILayoutable startToStart, ILayoutable endToEnd, ILayoutable topToTop, ILayoutable botToBot) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToStart_Of(startToStart);
      configurator.layPoz_EndToEnd_Of(endToEnd);

      configurator.layPoz_BotToBot_Of(botToBot);
      configurator.layPoz_TopToTop_Of(topToTop);

      panel.addLayoutable(adapter);

      return adapter;
   }

   public void addLayoutableOnTop(ILayoutableSwing layoutable) {
      panel.addLayoutableNoCheck(layoutable);
   }

   public ILayoutable addLayoutableStartBot(JComponent c, ILayoutable start, ILayoutable end, ILayoutable botToBot) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToStart_Of(start);
      configurator.layPoz_EndToEnd_Of(end);

      configurator.layPoz_BotToBot_Of(botToBot);

      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);

      return adapter;
   }

   public ILayoutable addLayoutableStartEnd(JComponent c, ILayoutable start, ILayoutable end) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToStart_Of(start);
      configurator.layPoz_EndToEnd_Of(end);

      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);

      return adapter;
   }

   public ILayoutable addLayoutableStartEndInsideBelow(JComponent c, ILayoutable start, ILayoutable end, ILayoutable top) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToStart_Of(start);
      configurator.layPoz_EndToEnd_Of(end);

      configurator.layPoz_TopToBot_Of(top);
      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);

      return adapter;
   }

   public ILayoutable addLayoutableStartEndOutsideCenter(JComponent c, ILayoutable start, ILayoutable end, ILayoutable center) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToEnd_Of(start);
      configurator.layPoz_EndToStart_Of(end);

      configurator.layPoz_MidYToMid_Of(center);
      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);

      return adapter;
   }

   /**
    * Lays Component between parameters startToEnd, endTostart, topToBot, botToTop
    * 
    * @param c
    * @param start
    * @param end
    * @param top
    * @param bot
    */
   public ILayoutable addLayoutableStartEndTopBot(JComponent c, ILayoutable start, ILayoutable end, ILayoutable top, ILayoutable bot) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToStart_Of(start);
      configurator.layPoz_EndToEnd_Of(end);
      configurator.layPoz_TopToBot_Of(top);
      configurator.layPoz_BotToTop_Of(bot);

      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);
      return adapter;
   }

   public ILayoutable addLayoutableStartEndTopInsidePref(JComponent c, ILayoutable start, ILayoutable end, ILayoutable top) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToStart_Of(start);
      configurator.layPoz_EndToEnd_Of(end);

      configurator.layPoz_TopToTop_Of(top);

      configurator.laySiz_Preferred();
      panel.addLayoutable(adapter);

      return adapter;
   }

   public void addLayoutWillListener(ILayoutWillListener listener) {
   }

   /**
    * 
    * @param c
    * @param start
    * @param end
    * @param verticalSizer
    */
   public ILayoutable addStartEndTopTopBotBot(JComponent c, ILayoutable start, ILayoutable end, ILayoutable verticalSizer) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      Area2DConfigurator configurator = adapter.getLay();

      configurator.layPoz_StartToStart_Of(start);
      configurator.layPoz_EndToEnd_Of(end);

      configurator.layPoz_TopToTop_Of(verticalSizer);
      configurator.layPoz_BotToBot_Of(verticalSizer);

      panel.addLayoutable(adapter);
      return adapter;
   }

   /**
    * Create a chain whose parent is the Panel, owner of this
    * {@link ConfiguratorForSwing}.
    * 
    * The {@link LayoutableChainSwing} is itself an {@link ILayoutable} and can be
    * used to position other {@link ILayoutable} relative to it.
    * 
    * The {@link LayoutableChainSwing} is a ghost layoutable. It does not have any
    * visible artifacts on screen.
    * 
    * Chain size is by default set preferred.
    * 
    * @return
    */
   public LayoutableChainSwing createChainHorizontal() {
      LayoutableChainSwing chain = new LayoutableChainSwing(slc, panel);
      chain.setParent(panel);
      chain.setChainType(C.LINE_0_HORIZONTAL);
      chain.getLay().laySiz_Preferred();
      extras.add(chain);

      return chain;
   }

   public LayoutableChainSwing createChainVertical() {
      LayoutableChainSwing chain = new LayoutableChainSwing(slc, panel);
      chain.setParent(panel);
      chain.setChainType(C.LINE_1_VERTICAL);
      chain.getLay().laySiz_Preferred();
      extras.add(chain);

      return chain;
   }

   /**
    * Creates a Ghost whose parent is this panel.
    * 
    * @return
    */
   public LayoutableGhost createGhostLineHorizontal(int percent) {
      LayoutableGhost ghost = new LayoutableGhost(slc);
      ghost.setParent(panel);

      Area2DConfigurator lay = ghost.getLay();
      // empty
      ByteObject sizerEmpty = slc.getSizerFactory().getSizerEmptyLazy();
      lay.setSizerH(sizerEmpty);

      lay.layPoz_StartToStart_OfParent();
      lay.layPoz_EndToEnd_Parent();

      // now the X position is a ratio of the size
      ByteObject pozerTopToTop = slc.getPozerFactory().getPozerTopToTop();
      ByteObject sizerPercent = slc.getSizerFactory().getSizerRatio100Parent(percent);
      slc.getPozerFactory().setPoserWithSizer(pozerTopToTop, ITechPozer.POS_FUN_0_TOWARDS_CENTER, sizerPercent);

      lay.setPozerYTop(pozerTopToTop);

      extras.add(ghost);

      return ghost;
   }

   /**
    * Creates a Ghost whose parent is this panel.
    * 
    * @return
    */
   public LayoutableGhost createGhostLineVertical(int percent) {
      LayoutableGhost ghost = new LayoutableGhost(slc);
      ghost.setParent(panel);

      Area2DConfigurator lay = ghost.getLay();
      // empty
      ByteObject sizerEmpty = slc.getSizerFactory().getSizerEmptyLazy();
      lay.setSizerW(sizerEmpty);

      lay.layPoz_TopToTop_OfParent();
      lay.layPoz_BotToBot_OfParent();

      // now the X position is a ratio of the size
      ByteObject pozerStartToStart = slc.getPozerFactory().getPozerStartToStart();
      ByteObject sizerPercent = slc.getSizerFactory().getSizerRatio100Parent(percent);
      slc.getPozerFactory().setPoserWithSizer(pozerStartToStart, ITechPozer.POS_FUN_0_TOWARDS_CENTER, sizerPercent);

      lay.setPozerXStart(pozerStartToStart);

      extras.add(ghost);

      return ghost;
   }

   /**
    * 
    * @param c
    * @return
    */
   public LayoutableAdapterForJComponent createLayoutableFromSwingPref(JComponent c) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);

      adapter.getLay().laySiz_Preferred();

      panel.addLayoutableNoCheck(adapter);

      return adapter;
   }

   public LayoutableAdapterForJComponent createPanel(String string, Color color) {
      JPanel panel = new JPanel();
      panel.setName(string);
      panel.setBackground(color);

      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, panel);

      this.panel.addLayoutableNoCheck(adapter);
      return adapter;
   }

   public JPanelLayoutable getPanel() {
      return panel;
   }

   public void layoutInvalidate() {
      layoutInvalidatePosition();
      layoutInvalidateSize();
   }

   public void layoutInvalidatePosition() {
      for (int index = 0; index < extras.getLength(); index++) {
         ILayoutable o = (ILayoutable) extras.getObjectAtIndex(index);
         o.layoutInvalidatePosition();
      }

   }

   public void layoutInvalidateSize() {
      for (int index = 0; index < extras.getLength(); index++) {
         ILayoutable o = (ILayoutable) extras.getObjectAtIndex(index);
         o.layoutInvalidateSize();
      }
   }

   // #mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, ConfiguratorForSwing.class, "@line5");
      toStringPrivate(dc);
      dc.nlLvl(panel, "panel");

      dc.setExpand(true);
      dc.nlLvl(extras, "Extras");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ConfiguratorForSwing.class);
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {

   }

   // #enddebug

}
