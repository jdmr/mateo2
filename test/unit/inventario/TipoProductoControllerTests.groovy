package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(TipoProductoController)
@Mock(TipoProducto)
class TipoProductoControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/tipoProducto/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.tipoProductoInstanceList.size() == 0
        assert model.tipoProductoInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.tipoProductoInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.tipoProductoInstance != null
        assert view == '/tipoProducto/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/tipoProducto/show/1'
        assert controller.flash.message != null
        assert TipoProducto.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoProducto/list'


        def tipoProducto = new TipoProducto()

        // TODO: populate domain properties

        assert tipoProducto.save() != null

        params.id = tipoProducto.id

        def model = controller.show()

        assert model.tipoProductoInstance == tipoProducto
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoProducto/list'


        def tipoProducto = new TipoProducto()

        // TODO: populate valid domain properties

        assert tipoProducto.save() != null

        params.id = tipoProducto.id

        def model = controller.edit()

        assert model.tipoProductoInstance == tipoProducto
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoProducto/list'

        response.reset()


        def tipoProducto = new TipoProducto()

        // TODO: populate valid domain properties

        assert tipoProducto.save() != null

        // test invalid parameters in update
        params.id = tipoProducto.id

        controller.update()

        assert view == "/tipoProducto/edit"
        assert model.tipoProductoInstance != null

        tipoProducto.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/tipoProducto/show/$tipoProducto.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/tipoProducto/list'

        response.reset()

        def tipoProducto = new TipoProducto()

        // TODO: populate valid domain properties
        assert tipoProducto.save() != null
        assert TipoProducto.count() == 1

        params.id = tipoProducto.id

        controller.delete()

        assert TipoProducto.count() == 0
        assert TipoProducto.get(tipoProducto.id) == null
        assert response.redirectedUrl == '/tipoProducto/list'


    }


}