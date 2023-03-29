package clothing4you;

import java.awt.event.ActionEvent;

public class WishlistController {
    private WishlistModel model;
    private WishlistView view;
 //   private Catalog previousCatalog;

    public WishlistController(WishlistModel model, WishlistView view) {//, Catalog previousCatalog) {
        this.model = model;
        this.view = view;
        //       this.previousCatalog = previousCatalog;
        //view.createBackButton().addActionListener(e -> onBackButtonClick());
    //}

//    public void onBackButtonClick(ActionEvent actionEvent) {
//        view.dispose();
//        view.getPreviousCatalog().setVisible(true);
//    }


    // Add action listeners to buttons
        view.createBackButton().addActionListener(e -> {
            view.dispose();
            view.getPreviousCatalog().setVisible(true);
        });

        view.createAddToCartButton().addActionListener(e -> {
            int index = view.getSelectedIndex();
            if (index != -1) {
                model.addToCart(index);
                view.removeSelectedRow();
                view.showMessage(model.getCart().getItems().get(model.getCart().getItems().size() - 1).getName() + " added to cart.");
            } else {
                view.showMessage("Please select an item that is available.");
            }
        });

        view.createRemoveButton().addActionListener(e -> {
            int index = view.getSelectedIndex();
            if (index != -1) {
                model.removeFromWishlist(index);
                view.removeSelectedRow();
                view.showMessage("Item removed from wishlist.");
            } else {
                view.showMessage("Please select an item to remove.");
            }
        });
    }

//    WishlistController() {
//        this.model = new WishlistModel();
//        this.view = new WishlistView();
//    }

}