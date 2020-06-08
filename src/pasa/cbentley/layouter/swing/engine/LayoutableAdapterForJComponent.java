/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.layouter.src4.engine.Area2DConfigurator;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.interfaces.ILayoutDelegate;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.interfaces.ILayoutableSwing;

/**
 * Adapter by Composition for existing Swing {@link JComponent} such as
 * 
 * <li> {@link JButton}
 * <li> {@link JLabel}
 * <li> {@link JTextArea}
 * 
 * @author Charles Bentley
 *
 */
public class LayoutableAdapterForJComponent implements ILayoutableSwing {

   protected final JComponent       component;

   protected ILayoutDelegate        delegate;

   protected final LayEngineSwing   engine;

   protected final JComponentReal   real;

   protected final SwingLayouterCtx slc;

   public LayoutableAdapterForJComponent(SwingLayouterCtx slc, JComponent component) {
      this.slc = slc;

      //#debug
      slc.toStringCheckNull(component);

      this.component = component;
      this.real = new JComponentReal(slc, component);
      this.engine = new LayEngineSwing(slc, this, real, slc.getNewLayoutID());
   }

   public void addDependency(ILayoutable lay, int flags) {
      engine.addDependency(lay, flags);
   }

   public Zer2DArea getArea() {
      return engine.getArea();
   }

   public JComponent getComponent() {
      return component;
   }

   public ILayoutable[] getDependencies() {
      return engine.getDependencies();
   }

   public LayEngineSwing getEngine() {
      return engine;
   }

   public LayEngineSwing getEngineSwing() {
      return engine;
   }

   public Area2DConfigurator getLay() {
      return engine.getLay();
   }

   public ILayoutable getLayoutableDelegate(ILayoutable source) {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutable getLayoutableEtalon(int etalonType) {
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

   public ILayoutable getLayoutableParent() {
      return engine.getLayoutableParent();
   }

   public ILayoutable getLayoutableViewContext() {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutable getLayoutableViewPort() {
      // TODO Auto-generated method stub
      return null;
   }

   public ILayoutDelegate getLayoutDelegate() {
      // TODO Auto-generated method stub
      return null;
   }

   public int getLayoutID() {
      // TODO Auto-generated method stub
      return 0;
   }

   public int getPozeX() {
      return engine.getPozeX();
   }

   public int getPozeXComputed() {
      return engine.getPozeXComputed();
   }

   public int getPozeY() {
      return engine.getPozeY();
   }

   public int getPozeYComputed() {
      return engine.getPozeYComputed();
   }

   public int getSizeDrawnHeight() {
      return engine.getSizeDrawnHeight();
   }

   public int getSizeDrawnWidth() {
      return engine.getSizeDrawnWidth();
   }

   public int getSizeFontHeight() {
      return real.getFontHeight();
   }

   public int getSizeFontWidth() {
      return real.getFontWidth();
   }

   public int getSizePreferredHeight() {
      return real.getRealPrefHeight();
   }

   public int getSizePreferredWidth() {
      return real.getRealPrefWidth();
   }

   public int getSizePropertyValueH(int property) {
      return real.getSizePropertyValueH(property);
   }

   public int getSizePropertyValueW(int property) {
      return real.getSizePropertyValueH(property);
   }

   public Area2DConfigurator lay() {
      return engine.getLay();
   }

   public void layoutInvalidate() {
      engine.layoutInvalidate();
   }

   public void layoutInvalidatePosition() {
      engine.layoutInvalidatePosition();
   }

   public void layoutInvalidateSize() {
      engine.layoutInvalidateSize();

   }

   public boolean layoutIsValidPosition() {
      return engine.layoutIsValidPosition();
   }

   public boolean layoutIsValidSize() {
      return engine.layoutIsValidSize();
   }

   public void layoutUpdateDependencies(int type) {
      engine.layoutUpdateDependencies(type);
   }

   public void layoutUpdatePositionCheck() {
      engine.layoutUpdatePositionCheck();
   }

   public void layoutUpdatePositionXCheck() {
      engine.layoutUpdatePositionXCheck();
   }

   public void layoutUpdatePositionYCheck() {
      engine.layoutUpdatePositionYCheck();
   }

   public void layoutUpdateSizeCheck() {
      engine.layoutUpdateSizeCheck();
   }

   public void layoutUpdateSizeHCheck() {
      engine.layoutUpdateSizeHCheck();
   }

   public void layoutUpdateSizeWCheck() {
      engine.layoutUpdateSizeWCheck();

   }

   public void repaintLayoutable() {
      component.repaint();
   }

   public void setArea(Zer2DArea area) {
      engine.setArea(area);
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, LayoutableAdapterForJComponent.class);
      toStringPrivate(dc);
      dc.nlLvl(real, JComponentReal.class);
      dc.nlLvl(engine, LayEngineSwing.class);
   }


   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, LayoutableAdapterForJComponent.class);
      toStringPrivate(dc);
      dc.oneLine(real);
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   public String toStringName() {
      return component.getName();
   }

   private void toStringPrivate(Dctx dc) {

   }
   //#enddebug
}
