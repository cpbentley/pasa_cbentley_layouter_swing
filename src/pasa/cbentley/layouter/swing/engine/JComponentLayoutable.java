/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JComponent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.layouter.src4.engine.Area2DConfigurator;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.engine.Zer2DSizer;
import pasa.cbentley.layouter.src4.interfaces.ILayoutDelegate;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.interfaces.ILayoutableSwing;

/**
 * 
 * Adapter by inheritance.
 * 
 * Use this class if you want to create a custom {@link JComponent} that is a {@link ILayoutable}
 * 
 * @author Charles Bentley
 *
 */
public class JComponentLayoutable extends JComponent implements ILayoutableSwing {

   /**
    * 
    */
   private static final long        serialVersionUID = 3760546493246702192L;

   private LayEngineSwing           engineSwing;

   protected final JComponentReal   real;

   protected final SwingLayouterCtx slc;

   public JComponentLayoutable(SwingLayouterCtx slc) {
      this.slc = slc;
      this.real = new JComponentReal(slc, this);
      engineSwing = new LayEngineSwing(slc, this, real, slc.getNewLayoutID());
   }

   public void addDependency(ILayoutable lay, int flags) {
      engineSwing.addDependency(lay, flags);
   }

   public Zer2DArea getArea() {
      return engineSwing.getArea();
   }

   public JComponent getComponent() {
      return this;
   }

   public ILayoutable[] getDependencies() {
      return engineSwing.getDependencies();
   }

   public LayEngineSwing getEngineSwing() {
      return engineSwing;
   }

   public Area2DConfigurator getLay() {
      return engineSwing.getLay();
   }

   public ILayoutable getLayoutableDelegate(ILayoutable source) {
      return null;
   }

   public ILayoutable getLayoutableEtalon(int etalonType) {
      return null;
   }

   public ILayoutable getLayoutableID(int id) {
      return slc.getLayoutableID(id);
   }

   public ILayoutable getLayoutableNav(int dir) {
      return null;
   }

   public ILayoutable getLayoutableParent() {
      return engineSwing.getLayoutableParent();
   }

   public ILayoutable getLayoutableViewContext() {
      Container parent = this.getParent();
      if (parent instanceof ILayoutable) {
         return (ILayoutable) parent;
      }
      return null;
   }

   public ILayoutable getLayoutableViewPort() {
      return this;
   }

   public ILayoutDelegate getLayoutDelegate() {
      // TODO Auto-generated method stub
      return null;
   }

   public int getLayoutID() {
      return engineSwing.getLayoutID();
   }

   public int getPozeX() {
      return engineSwing.getPozeX();
   }

   public int getPozeXComputed() {
      return engineSwing.getPozeXComputed();
   }

   public int getPozeY() {
      return engineSwing.getPozeY();
   }

   public int getPozeYComputed() {
      return engineSwing.getPozeYComputed();
   }

   public int getSizeBorderHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeBorderWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizeContentHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeContentWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizeDrawnHeight() {
      return engineSwing.getSizeDrawnHeight();
   }

   public int getSizeDrawnWidth() {
      return engineSwing.getSizeDrawnWidth();
   }

   public int getSizeFontHeight() {
      return real.getFontHeight();
   }

   public int getSizeFontWidth() {
      return real.getFontWidth();
   }

   public int getSizePaddingHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizePaddingWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizePreferredHeight() {
      return engineSwing.getSizePreferredHeight();
   }

   public int getSizePreferredWidth() {
      return engineSwing.getSizePreferredWidth();
   }

   public int getSizePropertyValueH(int property) {
      return real.getSizePropertyValueH(property);
   }

   public int getSizePropertyValueW(int property) {
      return real.getSizePropertyValueH(property);
   }

   public ByteObject getSizerH() {
      return engineSwing.getArea().getSizerH();
   }

   public ByteObject getSizerW() {
      return engineSwing.getArea().getSizerW();
   }

   public int getSizeUnitHeight() {
      return getSizePreferredHeight();
   }

   public int getSizeUnitWidth() {
      return getSizePreferredWidth();
   }

   public int getWidthDrawn() {
      return this.getSizeDrawnWidth();
   }

   public int getWidthFont() {
      return this.getSizeFontWidth();
   }

   public int getWidthPreferred() {
      return this.getSizePreferredWidth();
   }

   public int getWidthUnit() {
      return this.getSizeUnitWidth();
   }

   public void layoutInvalidate() {
      engineSwing.layoutInvalidate();
   }

   public void layoutInvalidatePosition() {
      engineSwing.layoutInvalidatePosition();

   }

   public void layoutInvalidateSize() {
      engineSwing.layoutInvalidateSize();
   }

   public boolean layoutIsValidPosition() {
      return engineSwing.layoutIsValidPosition();
   }

   public boolean layoutIsValidSize() {
      return engineSwing.layoutIsValidSize();
   }

   public void layoutUpdateDependencies(int type) {
      engineSwing.layoutUpdateDependencies(type);
   }

   public void layoutUpdatePositionCheck() {
      engineSwing.layoutUpdatePositionCheck();
   }

   public void layoutUpdatePositionXCheck() {
      engineSwing.layoutUpdatePositionXCheck();
   }

   public void layoutUpdatePositionYCheck() {
      engineSwing.layoutUpdatePositionYCheck();

   }

   /**
    * 
    */
   public void layoutUpdateSizeCheck() {
      engineSwing.layoutUpdateSizeCheck();
   }

   public void layoutUpdateSizeHCheck() {
      engineSwing.layoutUpdateSizeHCheck();

   }

   public void layoutUpdateSizeWCheck() {
      engineSwing.layoutUpdateSizeWCheck();
   }

   @Override
   public void paint(Graphics g) {
      Color bg = getBackground();
      // Drawing primitive shapes
      g.setColor(bg); // set the drawing color
      int x = getX();
      int y = getY();
      int width = getWidth();
      int height = getHeight();

      //!the g has already be translated to origin of component!
      x = 0;
      y = 0;

      //Rectangle clipBounds = g.getClipBounds();
      //#debug
      //toDLog().pFlow("x="+x+" y="+y+" width="+width+" height="+height + " Color="+bg +"", null, BLComponent.class, "paintComponent", LVL_05_FINE, true);

      g.fillRect(x, y, width, height);
   }

   public void repaintLayoutable() {
      engineSwing.repaintLayoutable();
   }

   public void setArea(Zer2DArea area) {
      engineSwing.setArea(area);
   }

   public void setParentLayout(ILayoutable parentLayout) {
      this.engineSwing.setParentLayout(parentLayout);
   }

   public void setPozer(ByteObject pozerX, ByteObject pozerY) {
      engineSwing.getLay().setPozerXStart(pozerX);
      engineSwing.getLay().setPozerYTop(pozerY);
   }

   public void setSizer(ByteObject sizerW, ByteObject sizerH) {
      engineSwing.getLay().setSizer(new Zer2DSizer(slc, sizerW, sizerH));
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, JComponentLayoutable.class, 324);
      toStringPrivate(dc);
      slc.toSD().d((JComponent) this, dc.newLevel());
      dc.nlLvl(engineSwing, "layoutableAdapter");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, JComponentLayoutable.class);
      toStringPrivate(dc);
      slc.toSD().d1((JComponent) this, dc.newLevel1Line());
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   public String toStringName() {
      return engineSwing.toStringName();
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendWithSpaceIfNotNull('[', getName(), ']');
   }

   //#enddebug

}
