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

   private LayEngineSwing           layoutableAdapter;

   protected final JComponentReal   real;

   protected final SwingLayouterCtx slc;

   public JComponentLayoutable(SwingLayouterCtx slc) {
      this.slc = slc;
      this.real = new JComponentReal(slc, this);
      layoutableAdapter = new LayEngineSwing(slc, this, real, slc.getNewLayoutID());
   }

   public void addDependency(ILayoutable lay, int flags) {
      layoutableAdapter.addDependency(lay, flags);
   }

   public Zer2DArea getArea() {
      return layoutableAdapter.getArea();
   }

   public JComponent getComponent() {
      return this;
   }

   public ILayoutable[] getDependencies() {
      return layoutableAdapter.getDependencies();
   }

   public Area2DConfigurator getLay() {
      return layoutableAdapter.getLay();
   }

   public LayEngineSwing getLayoutableAdapter() {
      return layoutableAdapter;
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
      return layoutableAdapter.getLayoutableParent();
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
      return layoutableAdapter.getLayoutID();
   }

   public int getPozeX() {
      return layoutableAdapter.getPozeX();
   }

   public int getPozeXComputed() {
      return layoutableAdapter.getPozeXComputed();
   }

   public int getPozeY() {
      return layoutableAdapter.getPozeY();
   }

   public int getPozeYComputed() {
      return layoutableAdapter.getPozeYComputed();
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
      return layoutableAdapter.getSizeDrawnHeight();
   }

   public int getSizeDrawnWidth() {
      return layoutableAdapter.getSizeDrawnWidth();
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
      return layoutableAdapter.getSizePreferredHeight();
   }

   public int getSizePreferredWidth() {
      return layoutableAdapter.getSizePreferredWidth();
   }

   public int getSizePropertyValueH(int property) {
      return real.getSizePropertyValueH(property);
   }

   public int getSizePropertyValueW(int property) {
      return real.getSizePropertyValueH(property);
   }


   public ByteObject getSizerH() {
      return layoutableAdapter.getArea().getSizerH();
   }

   public ByteObject getSizerW() {
      return layoutableAdapter.getArea().getSizerW();
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
      layoutableAdapter.layoutInvalidate();
   }

   public void layoutInvalidatePosition() {
      layoutableAdapter.layoutInvalidatePosition();

   }

   public void layoutInvalidateSize() {
      layoutableAdapter.layoutInvalidateSize();
   }

   public boolean layoutIsValidPosition() {
      return layoutableAdapter.layoutIsValidPosition();
   }

   public boolean layoutIsValidSize() {
      return layoutableAdapter.layoutIsValidSize();
   }

   public void layoutUpdateDependencies(int type) {
      layoutableAdapter.layoutUpdateDependencies(type);
   }

   public void layoutUpdatePositionCheck() {
      layoutableAdapter.layoutUpdatePositionCheck();
   }

   public void layoutUpdatePositionXCheck() {
      layoutableAdapter.layoutUpdatePositionXCheck();
   }

   public void layoutUpdatePositionYCheck() {
      layoutableAdapter.layoutUpdatePositionYCheck();

   }

   /**
    * 
    */
   public void layoutUpdateSizeCheck() {
      layoutableAdapter.layoutUpdateSizeCheck();
   }

   public void layoutUpdateSizeHCheck() {
      layoutableAdapter.layoutUpdateSizeHCheck();

   }

   public void layoutUpdateSizeWCheck() {
      layoutableAdapter.layoutUpdateSizeWCheck();
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
      layoutableAdapter.repaintLayoutable();
   }

   public void setArea(Zer2DArea area) {
      layoutableAdapter.setArea(area);
   }

   public void setParentLayout(ILayoutable parentLayout) {
      this.layoutableAdapter.setParentLayout(parentLayout);
   }

   public void setPozer(ByteObject pozerX, ByteObject pozerY) {
      layoutableAdapter.getLay().setPozerXStart(pozerX);
      layoutableAdapter.getLay().setPozerYTop(pozerY);
   }

   public void setSizer(ByteObject sizerW, ByteObject sizerH) {
      layoutableAdapter.getLay().setSizer(new Zer2DSizer(slc, sizerW, sizerH));
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "BLComponent");
      toStringPrivate(dc);
      slc.toSD().d((JComponent) this, dc.newLevel());
      dc.nlLvl(layoutableAdapter, "layoutableAdapter");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "BLComponent");
      toStringPrivate(dc);
      slc.toSD().d1((JComponent) this, dc.newLevel1Line());
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   public String toStringName() {
      return layoutableAdapter.toStringName();
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendWithSpaceIfNotNull('[', getName(), ']');
   }

   //#enddebug

}
