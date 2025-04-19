package application.application.samples;

import application.application.model.Product;
import application.application.service.IProductService;
import application.application.service.implementation.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductSamples {

    IProductService productService;

    static public List<Product> returnGuitars() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .name("Fender American Professional II Stratocaster")
                .description("An iconic solid-body electric guitar with a comfortable neck and versatile tone.")
                .imageUrl("https://www.stars-music.com/medias/fender/strat-american-professional-ii-usa-rw-hd-171240.webp")
                .price(1699.99)
                .build());
        products.add(Product.builder()
                .name("Gibson Les Paul Standard 50s")
                .description("A classic solid-body electric guitar known for its warm, rich tone and sustain.")
                .imageUrl("https://thumbs.static-thomann.de/thumb/padthumb600x600/pics/bdb/_46/462509/18203706_800.jpg")
                .price(2799.00)
                .build());
        products.add(Product.builder()
                .name("Martin D-28")
                .description("A legendary acoustic guitar with a powerful and balanced sound, favored by many.")
                .imageUrl("https://www.stars-music.com/medias/martin-guitar/d28-standard-natural-gloss-hd-58587.jpg")
                .price(3099.00)
                .build());
        products.add(Product.builder()
                .name("Taylor GS Mini Mahogany")
                .description("A compact and portable acoustic guitar with a surprisingly full and rich sound.")
                .imageUrl("https://mooloolabamusic.com.au/assets/full/GSMINI-EMAH.png?20220125115629")
                .price(599.00)
                .build());
        products.add(Product.builder()
                .name("PRS Custom 24")
                .description("A high-quality electric guitar known for its versatility, playability, and stunning looks.")
                .imageUrl("https://sc1.musik-produktiv.com/pic-010135051xxl/prs-custom-24-black-goldburst.jpg")
                .price(3799.00)
                .build());
        return products;
    }

    static public List<Product> returnDrums() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .name("Pearl Export 5-Piece Drum Set")
                .description("A complete entry-level drum set with everything you need to get started.")
                .imageUrl("https://m.media-amazon.com/images/I/61PkX-c0evL.jpg")
                .price(799.00)
                .build());
        products.add(Product.builder()
                .name("Roland TD-17KVX Electronic Drum Set")
                .description("A high-quality electronic drum set with realistic sounds and feel for practice and performance.")
                .imageUrl("https://www.stars-music.com/medias/roland/cropped-td-17kvx-145942.jpeg")
                .price(1899.99)
                .build());
        products.add(Product.builder()
                .name("DW Design Series 5-Piece Drum Set")
                .description("A professional-quality drum set with exceptional tone and craftsmanship.")
                .imageUrl("https://www.drumazon.com/cdn/shop/files/DW-DRUM-WORKSHOP-DESIGN-SERIES-5-PIECE-DRUM-KIT-SATIN-WHITE-DRUMAZON_01.jpg?v=1725577492")
                .price(2299.00)
                .build());
        products.add(Product.builder()
                .name("Ludwig Breakbeats by Questlove 4-Piece Drum Set")
                .description("A compact and portable drum set designed for tight spaces and smaller gigs.")
                .imageUrl("https://m.media-amazon.com/images/I/71da5qMfRZL.jpg")
                .price(699.00)
                .build());
        products.add(Product.builder()
                .name("Yamaha Stage Custom Hip 5-Piece Shell Pack")
                .description("A compact set with great sound")
                .imageUrl("https://www.drumshack.co.uk/media/cache/huge/prod_img/Hip%20SurfGreen.jpg")
                .price(899.00)
                .build());
        return products;
    }

    static public List<Product> returnBasses() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .name("Fender American Professional II Jazz Bass")
                .description("A versatile and iconic electric bass guitar with a rich, balanced tone.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/AmProIIJB3TS-large.jpg")
                .price(1849.00)
                .build());
        products.add(Product.builder()
                .name("Music Man StingRay 5 Special")
                .description("A powerful and distinctive 5-string electric bass known for its punchy sound.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/SR5SpclHB-large.jpg")
                .price(2899.00)
                .build());
        products.add(Product.builder()
                .name("Spector NS-2")
                .description("A high-end electric bass with a sleek design, neck-through construction, and exceptional tone.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/NS2TribalGree-large.jpg")
                .price(4299.00)
                .build());
        products.add(Product.builder()
                .name("Ibanez SR500E")
                .description("A modern and ergonomic electric bass with a fast neck and versatile electronics.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/SR500EBWK-large.jpg")
                .price(849.99)
                .build());
        products.add(Product.builder()
                .name("Warwick Thumb BO 5")
                .description("An boutique bass option.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/ThumbBO5-large.jpg")
                .price(3999.00)
                .build());
        return products;
    }

    static public List<Product> returnKeys() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .name("Roland Juno-DS61")
                .description("A versatile 61-key synthesizer with a wide range of sounds and performance features.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/JunoDS61-large.jpg")
                .price(799.99)
                .build());
        products.add(Product.builder()
                .name("Yamaha MODX6")
                .description("A powerful 61-key synthesizer with FM and AWM2 sound engines for rich and expressive tones.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/MODX6-large.jpg")
                .price(1699.00)
                .build());
        products.add(Product.builder()
                .name("Korg Minilogue XD")
                .description("A 4-voice polyphonic analog synthesizer with digital effects and a sequencer.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/MinilogueXD-large.jpg")
                .price(649.99)
                .build());
        products.add(Product.builder()
                .name("Arturia KeyLab 61 MkII")
                .description("A professional 61-key MIDI controller with extensive software integration and a premium feel.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/KeyLab61MkII-large.jpg")
                .price(549.00)
                .build());
        products.add(Product.builder()
                .name("Nord Stage 3 88")
                .description("An 88-key stage keyboard with piano, organ, and synthesizer sections for professional performers.")
                .imageUrl("https://media.sweetwater.com/api/i/q-82,f-auto/images/items/720/Stage388-large.jpg")
                .price(4999.00)
                .build());
        return products;
    }

    public void addSamples() {
        ProductSamples.returnBasses().forEach(bass -> {
            productService.addProduct(bass, "Bass");
        });
        ProductSamples.returnDrums().forEach(drums -> {
            productService.addProduct(drums, "Drums");
        });
        ProductSamples.returnGuitars().forEach(guitar -> {
            productService.addProduct(guitar, "Guitar");
        });
        ProductSamples.returnKeys().forEach(keys -> {
            productService.addProduct(keys, "Keys");
        });
    }

}

/*
List<Product> guitars = ProductSamples.returnGuitars();
List<Product> drums = ProductSamples.returnDrums();
List<Product> basses = ProductSamples.returnBass();
List<Product> keys = ProductSamples.returnKeys();

            guitars.forEach(product -> {
        productService.addProduct(product, "Guitar");
            });

                    drums.forEach(product -> {
        productService.addProduct(product, "Drums");
            });

                    basses.forEach(product -> {
        productService.addProduct(product, "Bass");
            });

                    keys.forEach(product -> {
        productService.addProduct(product, "Keys");
            });

 */