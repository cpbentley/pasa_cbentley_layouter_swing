package pasa.cbentley.layouter.swing.widgets;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src5.structs.BufferTStrong;
import pasa.cbentley.layouter.src4.engine.LayoutableRect;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
import pasa.cbentley.layouter.src4.interfaces.ILayoutRequestListener;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.engine.LayouterLayout;

public class BLPanel extends JPanel implements ILayoutable, IStringable {

   /**
    * 
    */
   private BufferTStrong<LayoutableAdapter>   layers;

   private LayoutableAdapter                  layoutableAdapter;

   private Map<JComponent, LayoutableAdapter> map;

   protected final SwingLayouterCtx           slc;

   private int                                topCount;

   public BLPanel(SwingLayouterCtx slc) {
      this.slc = slc;
      this.setName("rootBLPanel");
      this.setLayout(new LayouterLayout(slc));
      map = new HashMap<JComponent, LayoutableAdapter>();
      layers = new BufferTStrong<LayoutableAdapter>(slc.getC5(), LayoutableAdapter.class, 10, 5, 2);

      layoutableAdapter = new LayoutableAdapter(slc, this);

      ByteObject pozerAt0 = slc.getFactoryPozer().getPozerAtPixel0();
      layoutableAdapter.setPozerXStart(pozerAt0);
      layoutableAdapter.setPozerYTop(pozerAt0);
   }

   private void addBLComponentValidate(BLComponent bl) {
      //validate the sizers and pozers
      Zer2DArea area = bl.getLayoutableAdapter().getArea();
      if (area.isInvalidArea()) {
         //#debug
         toDLog().pNull(area.toStringInvalidAreaMessage(), bl, BLPanel.class, "addBLComponentValidate", LVL_05_FINE, false);
         throw new IllegalArgumentException(bl.getName() + ":" + area.toStringInvalidAreaMessage());
      }

   }

   public void repaintLayoutable() {
      this.repaint();
   }

   public void addDependency(ILayoutable lay, int flags) {
      layoutableAdapter.addDependency(lay, flags);
   }

   /**
    * With preferred size
    * @param comp
    * @param pozerCorner1
    */
   public LayoutableAdapter addLayoutable(JComponent comp, Zer2DPozer pozerCorner1) {
      LayoutableAdapter adapter = new LayoutableAdapter(slc, comp);
      adapter.setPozerYTop(pozerCorner1.getPozerY());
      adapter.setPozerXStart(pozerCorner1.getPozerX());
      adapter.laySizePreferred();

      addLayoutable(adapter);
      return adapter;
   }

   public void addLayoutable(LayoutableAdapter adapter) {

      //validate the sizers and pozers
      if (adapter.getArea().isInvalidArea()) {
         //#debug
         toDLog().pNull("Invalid Area", adapter, BLPanel.class, "addLayoutable", LVL_05_FINE, false);
         throw new IllegalArgumentException();
      }

      map.put(adapter.getComponent(), adapter);
      layers.add(adapter);
   }

   public void addLayoutableBack(BLComponent bl) {
      addBLComponentValidate(bl);
      layers.addLeft(bl.getLayoutableAdapter());
   }

   public void addLayoutableOnTop(BLComponent bl) {
      addBLComponentValidate(bl);
      layers.add(bl.getLayoutableAdapter());
   }

   /**
    * 
    * A {@link ILayoutable} that represents the area
    * 
    * <li>{@link ITechLayout#VIEW_STYLE_00_VIEW_FULL}
    * 
    * And 
    * 
    * <li>{@link ITechLayout#VIEW_STRUCT_00_ALL_VISIBLE}
    * 
    * @param subView
    * @return
    */
   public void buildLayoutableRectX(LayoutableRect copy, int subView, int subArea) {

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

   public ILayoutable getLayoutableDelegate(ILayoutable source) {
      return null;
   }

   public ILayoutable getLayoutableID(int id) {
      return null;
   }

   public ILayoutable getLayoutableNav(int dir) {
      return null;
   }

   public ILayoutable getLayoutableParent() {
      return null;
   }

   public ILayoutable getLayoutableViewContext() {
      return this;
   }

   public ILayoutable getLayoutableViewPort() {
      return this;
   }

   public LayoutableAdapter getLayoutAdapterFor(Component c) {
      return map.get(c);
   }

   public int getLayoutID() {
      return layoutableAdapter.getLayoutID();
   }

   public ILayoutRequestListener getLayoutRequestListener() {
      return null;
   }

   public int getPozeX() {
      return getX();
   }

   public int getPozeXComputed() {
      //its always computed
      return getX();
   }

   public int getPozeY() {
      return getY();
   }

   public int getPozeYComputed() {
      //its always computed
      return getY();
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
      return getHeight();
   }

   public int getSizeDrawnWidth() {
      return getWidth();
   }

   public int getSizeFromDeletgateHeight() {
      return 0;
   }

   public int getSizeFromDeletgateWidth() {
      return 0;
   }

   public int getSizePaddingHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizePaddingWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizePreferredHeight() {
      return getPreferredSize().height;
   }

   public int getSizeMaxHeight(ILayoutable layoutable) {
      return this.getHeight();
   }

   public int getSizeMaxWidth(ILayoutable layoutable) {
      return this.getWidth();
   }

   public int getSizePreferredWidth() {
      return getPreferredSize().width;
   }

   public int getSizeUnitHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeUnitWidth() {
      return getSizeDrawnWidth();
   }

   public int getWidthDelegate() {
      return getSizeFromDeletgateWidth();
   }

   public int getWidthDrawn() {
      return getSizeDrawnWidth();
   }

   public int getWidthFont() {
      return getFontWidth();
   }

   public int getWidthPreferred() {
      return getSizeUnitHeight();
   }

   public int getWidthUnit() {
      return getSizeUnitWidth();
   }

   public void layoutInvalidate() {
   }

   public void layoutInvalidatePosition() {

   }

   public void layoutInvalidateSize() {
   }

   public boolean layoutIsValidPosition() {
      return true;
   }

   public boolean layoutIsValidSize() {
      return true;
   }

   public void layoutUpdateDependencies(int type) {
      layoutableAdapter.layoutUpdateDependencies(type);
   }

   public void layoutUpdatePosition() {
      //its always computed
   }

   public void layoutUpdatePositionCheck() {
      //its always computed
   }

   public void layoutUpdatePositionXCheck() {
      //its always computed
   }

   public void layoutUpdatePositionYCheck() {
      //its always computed
   }

   public void layoutUpdateSize() {
      //we don't need to update our size.. its always update to data
   }

   public void layoutUpdateSizeCheck() {
      //its always computed
   }

   public void layoutUpdateSizeHCheck() {
      //its always computed
   }

   public void layoutUpdateSizeWCheck() {
      //its always computed
   }

   public void layoutSoftValidation() {
      this.removeAll();
      int size = layers.getSize();
      for (int i = size - 1; i >= 0; i--) {
         LayoutableAdapter layoutableAdapter = (LayoutableAdapter) layers.get(i);
         this.add(layoutableAdapter.getComponent());
      }
      this.revalidate();
      this.repaint();
   }

   public void layoutUpdateStart() {

      //#debug
      toDLog().pFlow("", this, BLPanel.class, "layoutUpdateStart", LVL_05_FINE, true);

      this.removeAll();

      //now add them 
      int size = layers.getSize();
      for (int i = size - 1; i >= 0; i--) {
         LayoutableAdapter layoutableAdapter = (LayoutableAdapter) layers.get(i);
         //invalidate its layout
         layoutableAdapter.layoutInvalidate();
         this.add(layoutableAdapter.getComponent());
      }
   }

   public void setArea(Zer2DArea area) {
      layoutableAdapter.setArea(area);
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "BLPanel");
      toStringPrivate(dc);

      int size = layers.getSize();
      for (int i = size - 1; i >= 0; i--) {
         LayoutableAdapter layoutableAdapter = (LayoutableAdapter) layers.get(i);
         dc.nlLvl(layoutableAdapter);
      }

      //regular swing debug data
      slc.d((JPanel) this, dc.nLevel());
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "BLPanel");
      toStringPrivate(dc);
      slc.d1((JComponent) this, dc.nLevel1Line());
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   public String toStringName() {
      return layoutableAdapter.toStringName();
   }

   private void toStringPrivate(Dctx dc) {
      int size = layers.getSize();
      dc.appendVarWithSpace("#layers", size);

   }

   //#enddebug

}
