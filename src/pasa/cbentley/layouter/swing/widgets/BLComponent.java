package pasa.cbentley.layouter.swing.widgets;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JComponent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.engine.Zer2DSizer;
import pasa.cbentley.layouter.src4.interfaces.ILayoutRequestListener;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;

public class BLComponent extends JComponent implements ILayoutable {

   private LayoutableAdapter        layoutableAdapter;

   private ILayoutRequestListener   requestListener;

   protected final SwingLayouterCtx slc;

   public BLComponent(SwingLayouterCtx slc) {
      this.slc = slc;
      layoutableAdapter = new LayoutableAdapter(slc, this);
   }

   public BLComponent(SwingLayouterCtx slc, int layoutID) {
      this.slc = slc;
      layoutableAdapter = new LayoutableAdapter(slc, this, layoutID);
   }

   public void addDependency(ILayoutable lay, int flags) {
      layoutableAdapter.addDependency(lay, flags);
   }

   public void layoutInvalidatePosition() {
      layoutableAdapter.layoutInvalidatePosition();

   }

   public void repaintLayoutable() {
      layoutableAdapter.repaintLayoutable();
   }

   public int getSizeMaxHeight(ILayoutable layoutable) {
      return layoutableAdapter.getSizeMaxHeight(layoutable);
   }

   public int getSizeMaxWidth(ILayoutable layoutable) {
      return layoutableAdapter.getSizeMaxWidth(layoutable);
   }

   public void layoutUpdatePositionXCheck() {
      layoutableAdapter.layoutUpdatePositionXCheck();
   }

   public void layoutUpdatePositionYCheck() {
      layoutableAdapter.layoutUpdatePositionYCheck();

   }

   public void layoutUpdateSizeHCheck() {
      layoutableAdapter.layoutUpdateSizeHCheck();

   }

   public void layoutUpdateSizeWCheck() {
      layoutableAdapter.layoutUpdateSizeWCheck();
   }

   public void layoutInvalidateSize() {
      layoutableAdapter.layoutInvalidateSize();
   }

   public Zer2DArea getArea() {
      return layoutableAdapter.getArea();
   }

   public ILayoutable[] getDependencies() {
      return layoutableAdapter.getDependencies();
   }

   public int getFontHeight() {
      return layoutableAdapter.getFontHeight();
   }

   public int getFontWidth() {
      return layoutableAdapter.getFontWidth();
   }

   public int getSizePaddingWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizePaddingHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeBorderWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizeBorderHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeContentWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizeContentHeight() {
      return getSizeDrawnHeight();
   }

   public LayoutableAdapter getLayoutableAdapter() {
      return layoutableAdapter;
   }

   public ILayoutable getLayoutableDelegate(ILayoutable source) {
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

   public int getLayoutID() {
      return layoutableAdapter.getLayoutID();
   }

   public ILayoutRequestListener getLayoutRequestListener() {
      return requestListener;
   }

   public ILayoutable getParentLayout() {
      return layoutableAdapter.getParentLayout();
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

   public int getSizeDrawnHeight() {
      return layoutableAdapter.getSizeDrawnHeight();
   }

   public int getSizeDrawnWidth() {
      return layoutableAdapter.getSizeDrawnWidth();
   }

   public int getSizeFromDeletgateHeight() {
      return layoutableAdapter.getSizeFromDeletgateHeight();
   }

   public int getSizeFromDeletgateWidth() {
      return layoutableAdapter.getSizeFromDeletgateWidth();
   }

   public int getSizePreferredHeight() {
      return layoutableAdapter.getSizePreferredHeight();
   }

   public int getSizePreferredWidth() {
      return layoutableAdapter.getSizePreferredWidth();
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

   public int getWidthDelegate() {
      return this.getSizeFromDeletgateWidth();

   }

   public int getWidthDrawn() {
      return this.getSizeDrawnWidth();
   }

   public int getWidthFont() {
      return this.getFontWidth();
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

   public boolean layoutIsValidPosition() {
      return layoutableAdapter.layoutIsValidPosition();
   }

   public boolean layoutIsValidSize() {
      return layoutableAdapter.layoutIsValidSize();
   }

   public void layoutUpdateDependencies(int type) {
      layoutableAdapter.layoutUpdateDependencies(type);
   }

   public void layoutUpdatePosition() {
      layoutableAdapter.layoutUpdatePosition();
   }

   public void layoutUpdatePositionCheck() {
      layoutableAdapter.layoutUpdatePositionCheck();
   }

   /**
    * 
    */
   public void layoutUpdateSizeCheck() {
      layoutableAdapter.layoutUpdateSizeCheck();
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

   public void setArea(Zer2DArea area) {
      layoutableAdapter.setArea(area);
   }

   public void setParentLayout(ILayoutable parentLayout) {
      this.layoutableAdapter.setParentLayout(parentLayout);
   }

   public void setPozer(ByteObject pozerX, ByteObject pozerY) {
      layoutableAdapter.setPozerXStart(pozerX);
      layoutableAdapter.setPozerYTop(pozerY);
   }

   public void setRequestListener(ILayoutRequestListener requestListener) {
      this.requestListener = requestListener;
   }

   public void setSizer(ByteObject sizerW, ByteObject sizerH) {
      layoutableAdapter.setSizer(new Zer2DSizer(slc, sizerW, sizerH));
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
      slc.d((JComponent) this, dc.nLevel());
      dc.nlLvl(layoutableAdapter, "layoutableAdapter");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public String toStringName() {
      return layoutableAdapter.toStringName();
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "BLComponent");
      toStringPrivate(dc);
      slc.d1((JComponent) this, dc.nLevel1Line());
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendWithSpaceIfNotNull('[', getName(), ']');
   }

   //#enddebug

}
