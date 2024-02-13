/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

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
import pasa.cbentley.layouter.src4.ctx.LayouterCtx;
import pasa.cbentley.layouter.src4.engine.Area2DConfigurator;
import pasa.cbentley.layouter.src4.engine.LayoutableRect;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
import pasa.cbentley.layouter.src4.interfaces.ILayoutDelegate;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.interfaces.ILayoutableSwing;

/**
 * Provides utility methods to layout Swing {@link JComponent}s use the {@link LayouterCtx} engine.
 * 
 * @author Charles Bentley
 *
 */
public class JPanelLayoutable extends JPanel implements ILayoutable, IStringable {

   /**
    * 
    */
   private static final long                 serialVersionUID = 6957176235537979235L;

   /**
    * 
    */
   private BufferTStrong<ILayoutableSwing>   layers;

   private LayoutableAdapterForJComponent    compLay;

   private Map<JComponent, ILayoutableSwing> map;

   protected final SwingLayouterCtx          slc;

   private int                               topCount;

   protected ConfiguratorForSwing            configurator;

   public JPanelLayoutable(SwingLayouterCtx slc) {
      this.slc = slc;
      configurator = new ConfiguratorForSwing(slc, this);

      this.setName("rootPanelLayouterEngine");
      this.setLayout(new LayoutManagerForLayouter(slc));

      map = new HashMap<JComponent, ILayoutableSwing>();
      layers = new BufferTStrong<ILayoutableSwing>(slc.getC5(), ILayoutableSwing.class, 10, 5, 2);

      compLay = new LayoutableAdapterForJComponent(slc, this);

      ByteObject pozerAt0 = slc.getFactoryPozer().getPozerAtPixel0();
      compLay.getLay().setPozerXStart(pozerAt0);
      compLay.getLay().setPozerYTop(pozerAt0);
   }

   public ConfiguratorForSwing getConfigurator() {
      return configurator;
   }

   public void setDependency(ILayoutable lay, int flags) {
      compLay.setDependency(lay, flags);
   }

   /**
    * Returns the {@link LayoutableAdapterForJComponent} to configure the layout of this {@link JComponent}
    * 
    * @param comp
    * @return
    */
   public LayoutableAdapterForJComponent addLayoutable(JComponent comp) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, comp);
      AddNoCheck(adapter);
      return adapter;
   }

   /**
    * Adds the {@link JComponent} to the Panel. Convenience method around the {@link Area2DConfigurator}.
    * <br>
    * Sizes it with its preferred size
    * Configures the top left coordinate of {@link JComponent} to be positioned to the coordinate
    * defined by the {@link Zer2DPozer}. 
    * <br>
    * <br>
    * Its your responsibility to create the {@link Zer2DPozer} 
    * @param comp
    * @param pozerCorner1
    */
   public LayoutableAdapterForJComponent addLayoutable(JComponent comp, Zer2DPozer pozerCorner1) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, comp);
      Area2DConfigurator configurator = adapter.getLay();
      configurator.setPozerYTop(pozerCorner1.getPozerY());
      configurator.setPozerXStart(pozerCorner1.getPozerX());
      configurator.laySiz_Preferred();

      addLayoutable(adapter);
      return adapter;
   }

   /**
    * 
    * @param comp
    * @param pozerAnchorDest
    * @return
    */
   public LayoutableAdapterForJComponent addLayoutablePrefBotLeft(JComponent comp, Zer2DPozer pozerAnchorDest) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, comp);
      Area2DConfigurator configurator = adapter.getLay();
      configurator.setPozerYBot(pozerAnchorDest.getPozerY());
      configurator.setPozerXStart(pozerAnchorDest.getPozerX());
      configurator.laySiz_Preferred();

      addLayoutable(adapter);
      return adapter;
   }

   /**
    * Adds the {@link LayoutableAdapterForJComponent} to the panel.
    * 
    * It must have a valid 2d area defined.
    * @param adapter
    * 
    * @throws IllegalArgumentException if {@link Zer2DArea} is invalid.
    */
   public void addLayoutable(LayoutableAdapterForJComponent adapter) {
      //validate the sizers and pozers
      if (adapter.getLay().getArea().isInvalidArea()) {
         //#debug
         toDLog().pNull("Invalid Area", adapter, JPanelLayoutable.class, "addLayoutable", LVL_05_FINE, false);
         throw new IllegalArgumentException("");
      }

      AddNoCheck(adapter);
   }

   public void removeLayoutble(ILayoutableSwing layout) {
      Component c = layout.getComponent();
      map.remove(c);
      layers.removeAll(layout);
   }

   /**
    * 
    * @param adapter
    */
   public void addLayoutableNoCheck(ILayoutableSwing adapter) {

      AddNoCheck(adapter);
   }

   private void AddNoCheck(ILayoutableSwing adapter) {
      map.put(adapter.getComponent(), adapter);
      layers.add(adapter);
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
      return compLay.getArea();
   }

   public ILayoutable[] getDependencies() {
      return compLay.getDependencies();
   }

   public int getSizeFontHeight() {
      return compLay.getSizeFontHeight();
   }

   public int getSizeFontWidth() {
      return compLay.getSizeFontWidth();
   }

   public ILayoutable getLayoutableDelegate(ILayoutable source) {
      return null;
   }

   public ILayoutable getLayoutableEtalon(int etalonType) {
      return compLay.getLayoutableEtalon(etalonType);
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

   public ILayoutableSwing getLayoutAdapterFor(Component c) {
      return map.get(c);
   }

   public int getLayoutID() {
      return compLay.getLayoutID();
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

   public int getSizePropertyValueW(int property) {
      switch (property) {
         case ITechLayout.SIZER_PROP_00_DRAWN:
            return getSizeDrawnWidth();
         case ITechLayout.SIZER_PROP_01_PREFERRED:
            return getSizePreferredWidth();
         case ITechLayout.SIZER_PROP_03_FONT:
            return getSizeFontWidth();
         default:
            break;
      }
      return 0;
   }

   public int getSizePropertyValueH(int property) {
      switch (property) {
         case ITechLayout.SIZER_PROP_00_DRAWN:
            return getSizeDrawnHeight();
         case ITechLayout.SIZER_PROP_01_PREFERRED:
            return getSizePreferredHeight();
         case ITechLayout.SIZER_PROP_03_FONT:
            return getSizeFontHeight();
         default:
            break;
      }
      return 0;
   }

   public int getSizeMaxHeight(ILayoutable layoutable) {
      return this.getHeight();
   }

   public int getSizeMaxWidth(ILayoutable layoutable) {
      return this.getWidth();
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

   public int getSizePreferredWidth() {
      return getPreferredSize().width;
   }

   public int getSizeUnitHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeUnitWidth() {
      return getSizeDrawnWidth();
   }

   public void layoutInvalidate() {
      configurator.layoutInvalidate();
   }

   public void layoutInvalidatePosition() {
      configurator.layoutInvalidatePosition();
   }

   public void layoutInvalidateSize() {
      configurator.layoutInvalidateSize();
   }

   public boolean layoutIsValidPosition() {
      return true;
   }

   public boolean layoutIsValidSize() {
      return true;
   }

   /**
    * 
    */
   public void layoutSoftValidation() {
      this.removeAll();
      int size = layers.getSize();
      for (int i = size - 1; i >= 0; i--) {
         ILayoutableSwing layoutableAdapter = (ILayoutableSwing) layers.get(i);
         this.add(layoutableAdapter.getComponent());
      }
      this.revalidate();
      this.repaint();
   }

   public void layoutUpdateDependencies(int type) {
      compLay.layoutUpdateDependencies(type);
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

   public void layoutUpdateStart() {

      //#debug
      toDLog().pFlow("", this, JPanelLayoutable.class, "layoutUpdateStart", LVL_05_FINE, true);

      this.removeAll();

      layoutInvalidate();

      //now add them 
      int size = layers.getSize();
      for (int i = size - 1; i >= 0; i--) {
         ILayoutableSwing layoutableAdapter = (ILayoutableSwing) layers.get(i);
         //invalidate its layout
         layoutableAdapter.layoutInvalidate();
         JComponent component = layoutableAdapter.getComponent();

         this.add(component);
      }
      
      //TODO revalidate components by starting with those without dependencies
      //right now it valides on demand but this may lead of stackoverflow if you have a long
      //chain of dependencies
   }

   public void repaintLayoutable() {
      this.repaint();
   }

   public void setArea(Zer2DArea area) {
      compLay.setArea(area);
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, JPanelLayoutable.class);
      toStringPrivate(dc);

      dc.nlLvl(configurator, "configurator");

      int size = layers.getSize();
      for (int i = size - 1; i >= 0; i--) {
         ILayoutableSwing layoutableAdapter = (ILayoutableSwing) layers.get(i);
         dc.nlLvl(layoutableAdapter);
      }

      //regular swing debug data
      slc.toSD().d((JPanel) this, dc.newLevel());
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, JPanelLayoutable.class);
      toStringPrivate(dc);
      slc.toSD().d1((JComponent) this, dc.newLevel1Line());
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   public String toStringName() {
      return compLay.toStringName();
   }

   private void toStringPrivate(Dctx dc) {
      int size = layers.getSize();
      dc.appendVarWithSpace("#layers", size);

   }

   public ILayoutDelegate getLayoutDelegate() {
      // TODO Auto-generated method stub
      return null;
   }

   //#enddebug

}
