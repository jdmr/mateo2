package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(ProductoController)
@Mock(Producto)
class ProductoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/producto/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.productoInstanceList.size() == 0
        assert model.productoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.productoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.productoInstance != null
        assert view == '/producto/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/producto/show/1'
        assert controller.flash.message != null
        assert Producto.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/producto/list'


        def producto = new Producto()

        // TODO: populate domain properties

        assert producto.save() != null

        params.id = producto.id

        def model = controller.show()

        assert model.productoInstance == producto
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/producto/list'


        def producto = new Producto()

        // TODO: populate valid domain properties

        assert producto.save() != null

        params.id = producto.id

        def model = controller.edit()

        assert model.productoInstance == producto
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/producto/list'

        response.reset()


        def producto = new Producto()

        // TODO: populate valid domain properties

        assert producto.save() != null

        // test invalid parameters in update
        params.id = producto.id

        controller.update()

        assert view == "/producto/edit"
        assert model.productoInstance != null

        producto.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/producto/show/$producto.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/producto/list'

        response.reset()

        def producto = new Producto()

        // TODO: populate valid domain properties
        assert producto.save() != null
        assert Producto.count() == 1

        params.id = producto.id

        controller.delete()

        assert Producto.count() == 0
        assert Producto.get(producto.id) == null
        assert response.redirectedUrl == '/producto/list'


    }


}